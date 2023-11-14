package synApps.refit.diet.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.diet.dto.request.DietRecordRequest;
import synApps.refit.diet.service.DietRecordService;
import synApps.refit.global.dto.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/diet-record")
@RequiredArgsConstructor
public class DietRecordController {
    private final DietRecordService dietRecordService;

    @Operation(summary = "Save DietRecord", description = "식단 정보 저장")
    @PostMapping("/{schedule-id}")
    public ResponseEntity<?> saveDietRecord(
            @PathVariable("schedule-id") final Long scheduleId,
            @RequestBody DietRecordRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(dietRecordService.saveInfo(scheduleId, request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get DietRecord", description = "식단 정보 가져오기")
    @GetMapping("/{record-id}")
    public ResponseEntity<?> getDietRecord(
            @PathVariable("record-id") final Long recordId) {
        ResponseDto response = new ResponseDto(true, List.of(dietRecordService.getInfo(recordId)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Edit DietRecord", description = "식단 정보 수정")
    @PutMapping("/{record-id}")
    public ResponseEntity<?> editDietRecord(
            @PathVariable("record-id") final Long recordId,
            @RequestBody DietRecordRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(dietRecordService.modifyInfo(recordId, request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete DietRecord", description = "식단 정보 삭제")
    @DeleteMapping("/{record-id}")
    public ResponseEntity<?> deleteDietRecord(
            @PathVariable("record-id") final Long recordId) {
        ResponseDto response = new ResponseDto(true, List.of(dietRecordService.deleteInfo(recordId)));
        return ResponseEntity.ok(response);
    }
}
