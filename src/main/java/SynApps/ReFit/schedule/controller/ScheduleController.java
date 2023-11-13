package synApps.refit.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.global.dto.ResponseDto;
import synApps.refit.schedule.dto.request.ScheduleRequest;
import synApps.refit.schedule.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping()
    public ResponseEntity<?> saveSchedule(
            @RequestBody ScheduleRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(scheduleService.saveInfo(request;
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{schedule-id}")
    public ResponseEntity<?> getSchedule(
            @PathVariable("schedule-id") final Long scheduleId) {
        ResponseDto response = new ResponseDto(true, List.of(scheduleService.getSchedule(scheduleId)));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{schedule-id}")
    public ResponseEntity<?> editSchedule(
            @PathVariable("schedule-id") final Long scheduleId,
            @RequestBody ScheduleRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(scheduleService.modifyInfo(scheduleId, request)));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{schedule-id}")
    public ResponseEntity<?> deleteSchedule(
            @PathVariable("schedule-id") final Long scheduleId) {
        ResponseDto response = new ResponseDto(true, List.of(scheduleService.deleteInfo(scheduleId)));
        return ResponseEntity.ok(response);
    }
}
