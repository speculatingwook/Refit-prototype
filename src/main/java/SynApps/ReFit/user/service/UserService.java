package synApps.refit.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.user.dto.request.SignupRequest;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.oauth.entity.ProviderType;
import synApps.refit.user.oauth.entity.RoleType;
import synApps.refit.user.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional
    public User signUp(SignupRequest request) throws Exception{
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if(!request.getPassword().equals(request.getCheckPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new Exception("중복되는 아이디입니다.");
        }

        User user = User.of(
                request.getUserId(),
                request.getUsername(),
                request.getEmail(),
                ProviderType.LOCAL,
                RoleType.USER,
                LocalDateTime.now(),
                LocalDateTime.now());
        user.encodePassword(request.getPassword());
        userRepository.save(user);
        return user;
    }
}
