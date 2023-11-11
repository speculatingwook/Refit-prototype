package synApps.refit.exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.exercise.dto.request.RoutineRequest;
import synApps.refit.exercise.service.RoutineService;
import synApps.refit.global.dto.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/routine")
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping("/{exercise-record-id}")
    public ResponseEntity<?> saveRoutine(
            @PathVariable("exercise-record-id") final Long recordId,
            @RequestBody RoutineRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(routineService.saveInfo(recordId, request)));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{routine-id}")
    public ResponseEntity<?> editRoutine(
            @PathVariable("routine-id") final Long routineId,
            @RequestBody RoutineRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(routineService.modifyInfo(routineId, request)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{routine-id}")
    public ResponseEntity<?> getRoutine(
            @PathVariable("routine-id") final Long routineId) {
        ResponseDto response = new ResponseDto(true, List.of(routineService.getInfo(routineId)));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/routine-id")
    public ResponseEntity<?> deleteRoutine(
            @PathVariable("routine-id") final Long routineId) {
        ResponseDto response = new ResponseDto(true, List.of(routineService.deleteInfo(routineId)));
        return ResponseEntity.ok(response);
    }
}
}
