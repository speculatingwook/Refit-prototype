package synApps.refit.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import synApps.refit.user.dto.request.LoginRequest;
import synApps.refit.user.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
