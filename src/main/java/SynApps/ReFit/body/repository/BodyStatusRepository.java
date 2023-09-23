package synApps.refit.body.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.body.entity.BodyStatus;
import synApps.refit.user.entity.user.User;

import java.util.List;

@Repository
public interface BodyStatusRepository extends JpaRepository<BodyStatus, Long> {
    BodyStatus findByBodyStatusId(Long bodyStatusId);
    List<BodyStatus> findAllByUser(User user);
}