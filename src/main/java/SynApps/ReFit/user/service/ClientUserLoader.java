package synApps.refit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class ClientUserLoader {
    private final UserRepository userRepository;
    public User getClientUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserId(principal.getUsername());
    }
}