package synApps.refit.exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.exercise.dto.request.RoutineSetRequest;
import synApps.refit.exercise.service.RoutineSetService;
import synApps.refit.global.dto.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/routine-set")
@RequiredArgsConstructor
public class RoutineSetController {
    private final RoutineSetService routineSetService;

    @PostMapping("/{routine-id}")
    public ResponseEntity<?> saveRoutineSet(
            @PathVariable("routine-id") final Long routineId,
            @RequestBody RoutineSetRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(routineSetService.saveInfo(routineId, request)));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{routine-set-id}")
    public ResponseEntity<?> editInfo(
            @PathVariable("routine-set-id") final Long routineSetId,
            @RequestBody RoutineSetRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(routineSetService.modifyInfo(routineSetId, request)));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{routine-set-id}")
    public ResponseEntity<?> checkDone(
            @PathVariable("routine-set-id") final Long routineSetId) {
        ResponseDto response = new ResponseDto(true, List.of(routineSetService.done(routineSetId)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{routine-set-id}")
    public ResponseEntity<?> getInfo(
            @PathVariable("routine-set-id") final Long routineSetId) {
        ResponseDto response = new ResponseDto(true, List.of(routineSetService.getInfo(routineSetId)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/{routine-id}")
    public ResponseEntity<?> getRoutineSetList(
            @PathVariable("routine-id") final Long routineId) {
        ResponseDto response = new ResponseDto(true, List.of(routineSetService.getRoutineSetList(routineId)));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{routine-set-id}")
    public ResponseEntity<?> deleteRoutineSet(
            @PathVariable("routine-set-id") final Long routineSetId) {
        ResponseDto response = new ResponseDto(true, List.of(routineSetService.deleteInfo(routineSetId)));
        return ResponseEntity.ok(response);
    }
}
