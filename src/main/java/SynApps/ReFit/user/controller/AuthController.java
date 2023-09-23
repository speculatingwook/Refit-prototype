package synApps.refit.user.controller;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import synApps.refit.global.config.properties.AppProperties;
import synApps.refit.global.dto.ResponseDto;
import synApps.refit.global.dto.ResponseHeader;
import synApps.refit.global.utils.CookieUtil;
import synApps.refit.global.utils.HeaderUtil;
import synApps.refit.user.dto.request.LoginRequest;
import synApps.refit.user.dto.response.AccessTokenResponse;
import synApps.refit.user.entity.user.UserRefreshToken;
import synApps.refit.user.oauth.entity.RoleType;
import synApps.refit.user.oauth.entity.UserPrincipal;
import synApps.refit.user.oauth.token.AuthToken;
import synApps.refit.user.oauth.token.AuthTokenProvider;
import synApps.refit.user.repository.UserRefreshTokenRepository;
import synApps.refit.user.service.AuthService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody LoginRequest loginRequest) {
        return authService.login(request, response, loginRequest);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken (HttpServletRequest request, HttpServletResponse response) {
        return authService.refresh(request, response);
    }

}
