package synApps.refit.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.exercise.entity.Routine;
import synApps.refit.exercise.entity.RoutineSet;

import java.util.List;

@Repository
public interface RoutineSetRepository extends JpaRepository<RoutineSet, Long> {
    RoutineSet findByRoutineSetId(Long routineSetId);
    List<RoutineSet> findAllByRoutine(Routine routine);
}
