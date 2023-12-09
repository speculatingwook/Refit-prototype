package synApps.refit.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.user.dto.request.AppleLoginRequest;
import synApps.refit.user.dto.request.LoginRequest;
import synApps.refit.user.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody LoginRequest loginRequest) {
        return authService.login(request, response, loginRequest);
    }

    @PostMapping("/apple")
    public ResponseEntity<?> appleLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AppleLoginRequest appleRequest
            ) {
        return authService.appleOAuthLogin(request, response, appleRequest);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken (HttpServletRequest request, HttpServletResponse response) {
        return authService.refresh(request, response);
    }
}