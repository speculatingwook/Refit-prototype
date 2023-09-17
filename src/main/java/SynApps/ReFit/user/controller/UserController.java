package synApps.refit.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import synApps.refit.user.dto.request.SignupRequest;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> join(@Valid @RequestBody SignupRequest request) throws Exception {
        return ResponseEntity.ok(userService.signUp(request));
    }

    @GetMapping
    public ResponseEntity<?> getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return ResponseEntity.ok(user);
    }
}
