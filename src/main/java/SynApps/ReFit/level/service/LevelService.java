package synApps.refit.level.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.level.entity.Level;
import synApps.refit.level.repository.LevelRepository;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.service.UserService;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final UserService userService;
    private final LevelRepository levelRepository;
    public Level getLevel() {
        User user = userService.getUser();
        return levelRepository.findLevelByUser(user);
    }

    @Transactional
    public Level saveLevel(Integer exp) {
        User user = userService.getUser();
        Level level = Level.of(user, exp);
        levelRepository.save(level);
        return level;
    }

    public Level initLevel() {
        User user = userService.getUser();
        Level level = Level.newLevel(user);
        levelRepository.save(level);
        return level;
    }

    @Transactional
    public Level updateLevel(Integer exp) {
        User user = userService.getUser();
        Level level = levelRepository.findLevelByUser(user);
        level.calculateLevel(exp);
        return level;
    }

    @Transactional
    public void deleteLevel() {
        User user = userService.getUser();
        Level level = levelRepository.findLevelByUser(user);
        levelRepository.delete(level);
    }
}
