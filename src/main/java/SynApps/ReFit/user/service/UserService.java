package synApps.refit.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
