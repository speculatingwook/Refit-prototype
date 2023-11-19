package synApps.refit.exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.exercise.dto.request.ExerciseRequest;
import synApps.refit.exercise.service.ExerciseService;
import synApps.refit.global.dto.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;

    @PostMapping("/total")
    public ResponseEntity<?> saveTotalExercise(
            @RequestBody ExerciseRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseService.saveTotalInfo(request)));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/partial")
    public ResponseEntity<?> savePartialExercse(
            @RequestBody ExerciseRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseService.savePartialInfo(request)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/{exercise-record-id}")
    public ResponseEntity<?> getExerciseList(
            @PathVariable("exercise-record-id") final Long recordId) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseService.getExerciseList(recordId)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{exercise-id}")
    public ResponseEntity<?> getExercise(
            @PathVariable("exercise-id") final Long exerciseId) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseService.getInfo(exerciseId)));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{exercise-id}")
    public ResponseEntity<?> addPartialExercise(
            @PathVariable("exercise-id") final Long exerciseId,
            @RequestBody ExerciseRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseService.addPartAndType(exerciseId, request)));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{exercise-id}")
    public ResponseEntity<?> deleteExercise(
            @PathVariable("exercise-id") final Long exerciseId) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseService.deleteInfo(exerciseId)));
        return ResponseEntity.ok(response);
    }
}
