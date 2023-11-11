package synApps.refit.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.exercise.entity.RoutineSet;

@Repository
public interface RoutineSetRepository extends JpaRepository<RoutineSet, Long> {
    RoutineSet findByRoutineSetId(Long routineSetId);
}
