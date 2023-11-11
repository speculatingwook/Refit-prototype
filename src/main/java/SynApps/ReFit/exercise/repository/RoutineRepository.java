package synApps.refit.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.exercise.entity.Routine;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    Routine findByRoutineId(Long routineId);
}
