package synApps.refit.body.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.body.entity.BodyStatus;

@Repository
public interface BodyStatusRepository extends JpaRepository<BodyStatus, Long> {
    BodyStatus findByBodyStatusId(Long bodyStatusId);
}