package synApps.refit.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.exercise.entity.ExerciseRecord;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
    ExerciseRecord findByExerciseRecordId(Long recordId);
}
