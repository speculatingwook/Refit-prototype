package synApps.refit.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.exercise.dto.request.RoutineRequest;
import synApps.refit.exercise.entity.Exercise;
import synApps.refit.exercise.entity.ExerciseRecord;
import synApps.refit.exercise.entity.Routine;
import synApps.refit.exercise.repository.ExerciseRecordRepository;
import synApps.refit.exercise.repository.ExerciseRepository;
import synApps.refit.exercise.repository.RoutineRepository;
import synApps.refit.exercise.repository.RoutineSetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;

    public Routine saveInfo(Long exerciseRecordId, RoutineRequest request) {
        ExerciseRecord record = exerciseRecordRepository.findByExerciseRecordId(exerciseRecordId);
        Routine routine = Routine.of(record, request.isText(), request.getUserInputRoutine(), LocalDateTime.now(), LocalDateTime.now());
        routineRepository.save(routine);
        return routine;
    }

    @Transactional
    public Routine modifyInfo(Long routineId, RoutineRequest request) {
        Routine routine = routineRepository.findByRoutineId(routineId);
        routine.modifyUserInputRoutine(request.getUserInputRoutine(),LocalDateTime.now());
        return routine;
    }

    public Routine getInfo(Long routineId) {
        return routineRepository.findByRoutineId(routineId);
    }



    public String deleteInfo(Long routineId) {
        Routine routine = routineRepository.findByRoutineId(routineId);
        routineRepository.delete(routine);
        return "delete successful";
    }

}
