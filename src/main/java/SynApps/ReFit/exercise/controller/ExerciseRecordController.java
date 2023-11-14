package synApps.refit.exercise.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.exercise.dto.request.ExerciseRecordRequest;
import synApps.refit.exercise.service.ExerciseRecordService;
import synApps.refit.global.dto.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/exercise-record")
@RequiredArgsConstructor
public class ExerciseRecordController {
    private final ExerciseRecordService exerciseRecordService;

    @Operation(summary = "save record", description = "운동 기록 저장")
    @PostMapping("/{schedule-id}")
    public ResponseEntity<?> saveExerciseRecord(
            @PathVariable("schedule-id") final Long scheduleId,
            @RequestBody ExerciseRecordRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseRecordService.saveInfo(scheduleId, request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "edit record", description = "운동 기록 수정")
    @PutMapping("/{exercise-record-id}")
    public ResponseEntity<?> editTime(
            @PathVariable("exercise-record-id") final Long recordId,
            @RequestBody ExerciseRecordRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseRecordService.modifyTime(recordId, request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "get record", description = "운동 기록 가져오기")
    @GetMapping("/{exercise-record-id}")
    public ResponseEntity<?> getInfo(
            @PathVariable("exercise-record-id") final Long recordId) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseRecordService.getInfo(recordId)));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{exercise-record-id}")
    public ResponseEntity<?> deleteInfo(
            @PathVariable("exercise-record-id") final Long recordId) {
        ResponseDto response = new ResponseDto(true, List.of(exerciseRecordService.deleteInfo(recordId)));
        return ResponseEntity.ok(response);
    }
}
