package synApps.refit.level.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.level.entity.Level;
import synApps.refit.user.entity.user.User;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Level findLevelByUser(User user);
}
