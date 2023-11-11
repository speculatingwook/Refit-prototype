package synApps.refit.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.exercise.dto.request.ExerciseRequest;
import synApps.refit.exercise.entity.*;
import synApps.refit.exercise.repository.ExerciseRecordRepository;
import synApps.refit.exercise.repository.ExerciseRepository;
import synApps.refit.exercise.repository.RoutineRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final RoutineRepository routineRepository;

    public Exercise saveTotalInfo(ExerciseRequest request) {
        Exercise exercise = Exercise.of(
                request.getExerciseName(),
                ExercisePart.of(request.getExercisePart()),
                ExerciseType.of(request.getExerciseType()),
                request.getExerciseReference(),
                request.getExerciseImageUrl());
        exerciseRepository.save(exercise);
        return exercise;
    }

    public Exercise savePartialInfo(ExerciseRequest request) {
        Exercise exercise = Exercise.of(
                request.getExerciseName(),
                ExercisePart.of(request.getExercisePart()),
                ExerciseType.of(request.getExerciseType()));
        exerciseRepository.save(exercise);
        return exercise;
    }

    public List<Exercise> getExerciseList(Long exerciseRecordId) {
        ExerciseRecord exerciseRecord = exerciseRecordRepository.findByExerciseRecordId(exerciseRecordId);
        List<Exercise> exerciseList = new ArrayList<>();
        for (Routine routine : exerciseRecord.getRoutineList()) {
            exerciseList.add(routine.getExercise());
        }
        return exerciseList;
    }

    public Exercise getInfo(Long exerciseId) {
        return exerciseRepository.findByExerciseId(exerciseId);
    }

    @Transactional
    public Exercise addPartAndType(Long exerciseId, ExerciseRequest request) {
        Exercise exercise = exerciseRepository.findByExerciseId(exerciseId);
        exercise.setReferenceAndImageUrl(request.getExerciseReference(), request.getExerciseImageUrl());
        return exercise;
    }

    public String deleteInfo(Long exerciseId) {
        Exercise exercise = exerciseRepository.findByExerciseId(exerciseId);
        exerciseRepository.delete(exercise);
        return "delete successful";
    }

}
