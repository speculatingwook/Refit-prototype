package synApps.refit.user.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import synApps.refit.global.config.properties.AppProperties;
import synApps.refit.global.dto.ResponseDto;
import synApps.refit.global.dto.ResponseHeader;
import synApps.refit.global.utils.CookieUtil;
import synApps.refit.global.utils.HeaderUtil;
import synApps.refit.user.dto.request.LoginRequest;
import synApps.refit.user.entity.user.UserRefreshToken;
import synApps.refit.user.oauth.entity.RoleType;
import synApps.refit.user.oauth.entity.UserPrincipal;
import synApps.refit.user.oauth.token.AuthToken;
import synApps.refit.user.oauth.token.AuthTokenProvider;
import synApps.refit.user.repository.UserRefreshTokenRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final static long THREE_DAYS_MSEC = 259200000;
    private final static String REFRESH_TOKEN = "refresh_token";

    public ResponseEntity<?> login(HttpServletRequest request,
                                    HttpServletResponse response,
                                    LoginRequest loginRequest) {
        String userId = loginRequest.getUserId();
        String userPassword = loginRequest.getPassword();

        Authentication authentication = getAuthentication(userId, userPassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Date now = new Date();
        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();

        AuthToken accessToken = createToken(userId, authentication, now);
        AuthToken refreshToken = getRefreshToken(now, refreshTokenExpiry);

        checkRefreshToken(userId, refreshToken);
        executeCookie(request, response, refreshTokenExpiry, refreshToken);

        ResponseDto responseData = new ResponseDto(true, List.of(accessToken));
        return ResponseEntity.ok(responseData);
    }

    public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        Date now = new Date();
        // access token 확인
        String accessToken = HeaderUtil.getAccessToken(request);
        AuthToken authToken = tokenProvider.convertAuthToken(accessToken);

        if (!authToken.validate()) {
            return errorResponse("accessToken이 올바르지 않습니다.");
        }

        // expired access token 인지 확인
        Claims claims = authToken.getExpiredTokenClaims();

        if (claims == null) {
            return errorResponse("아직 accessToken이 만료되지 않았습니다.");
        }

        String userId = claims.getSubject();
        RoleType roleType = RoleType.of(claims.get("role", String.class));
        String refreshToken = CookieUtil.getCookie(request, REFRESH_TOKEN)
                .map(Cookie::getValue)
                .orElse((null));

        AuthToken authRefreshToken = tokenProvider.convertAuthToken(refreshToken);

        if (authRefreshToken.validate()) {
            return errorResponse("refreshToken이 올바르지 않습니다.");
        }

        // userId refresh token 으로 DB 확인
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserIdAndRefreshToken(userId, refreshToken);

        if (userRefreshToken == null) {
            return errorResponse("refreshToken이 올바르지 않습니다.");
        }

        long validTime = calculateValidTime(authRefreshToken);

        // refresh 토큰 기간이 3일 이하로 남은 경우, refresh 토큰 갱신
        if (validTime <= THREE_DAYS_MSEC) {
            long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
            authRefreshToken = getRefreshToken(now, refreshTokenExpiry);
            userRefreshToken.setRefreshToken(authRefreshToken.getToken());

            executeCookie(request, response, refreshTokenExpiry, authRefreshToken);
        }

        return ResponseEntity.ok(new ResponseDto(true, List.of(createNewAccessToken(userId, roleType, now).getToken())));
    }


    private Authentication getAuthentication(String userId, String userPassword) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userId,
                        userPassword)
        );
    }

    private AuthToken createToken(String userId, Authentication authentication, Date currentTime) {
        return tokenProvider.createAuthToken(
                userId,
                ((UserPrincipal) authentication.getPrincipal()).getRoleType().getCode(),
                new Date(currentTime.getTime() + appProperties.getAuth().getTokenExpiry())
        );
    }

    private AuthToken getRefreshToken(Date currentTime, long refreshTokenExpiry) {
        return tokenProvider.createAuthToken(
                appProperties.getAuth().getTokenSecret(),
                new Date(currentTime.getTime() + refreshTokenExpiry)
        );
    }
    private AuthToken createNewAccessToken(String userId, RoleType roleType, Date currentTime) {
        return tokenProvider.createAuthToken(
                userId,
                roleType.getCode(),
                new Date(currentTime.getTime() + appProperties.getAuth().getTokenExpiry())
        );
    }

    private void checkRefreshToken(String userId, AuthToken refreshToken) {
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserId(userId);
        if (userRefreshToken == null) {
            // 없는 경우 새로 등록
            userRefreshToken = new UserRefreshToken(userId, refreshToken.getToken());
            userRefreshTokenRepository.saveAndFlush(userRefreshToken);
        } else {
            // DB에 refresh 토큰 업데이트
            userRefreshToken.setRefreshToken(refreshToken.getToken());
        }
    }

    private void executeCookie(HttpServletRequest request,
                               HttpServletResponse response,
                               long refreshTokenExpiry,
                               AuthToken refreshToken) {
        int cookieMaxAge = (int) refreshTokenExpiry / 60;
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);
    }
    private long calculateValidTime(AuthToken authToken) {
        Date now = new Date();
        return authToken.getTokenClaims().getExpiration().getTime() - now.getTime();
    }
    private ResponseEntity<?> errorResponse(String message) {
        return ResponseEntity.ok(new ResponseHeader(500, message));
    }
}
