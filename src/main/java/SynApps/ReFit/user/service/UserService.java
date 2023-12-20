package synApps.refit.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import synApps.refit.level.entity.Level;
import synApps.refit.level.service.LevelService;
import synApps.refit.user.dto.request.DuplicateIdRequest;
import synApps.refit.user.dto.request.SignupRequest;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.oauth.entity.ProviderType;
import synApps.refit.user.oauth.entity.RoleType;
import synApps.refit.user.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.existsByUserId(principal.getUsername())) {
            return userRepository.findByUserId(principal.getUsername());
        }
        return userRepository.findByEmail(principal.getUsername());
    }

    public User signUp(SignupRequest request) throws Exception {
        if (!request.getCode().equals("code")) {
            throw new Exception("코드가 일치하지 않습니다.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new Exception("중복되는 아이디입니다.");
        }

        User user = User.of(
                request.getUserId(),
                request.getUsername(),
                request.getEmail(),
                ProviderType.of(request.getProviderType()),
                RoleType.USER,
                LocalDateTime.now(),
                LocalDateTime.now());
        user.encodePassword(request.getPassword());
        userRepository.save(user);
        return user;
    }
//
//    public User signUpByOAuth(SignupRequest request){
//        ProviderType providerType = ProviderType.of(request.getProviderType());
//        if (userRepository.existsByUserId(request.getUserId())) {
//            return userRepository.findByProviderTypeAndUserId(providerType, request.getUserId());
//        }
//
//        userRepository.save(user);
//        return user;
//    }
    public boolean checkDuplicateId(DuplicateIdRequest request) throws Exception {
        return userRepository.existsByUserId(request.getUserId());
    }

    public String deleteUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUserId(principal.getUsername());
        userRepository.delete(user);
        return "delete successful";
    }
}