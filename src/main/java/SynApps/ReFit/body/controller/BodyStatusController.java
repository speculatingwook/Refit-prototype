package synApps.refit.body.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.body.dto.BodyStatusRequest;
import synApps.refit.body.service.BodyStatusService;
import synApps.refit.global.dto.ResponseDto;
import java.util.List;

@RestController
@RequestMapping("/body-status")
@RequiredArgsConstructor
public class BodyStatusController {
    private final BodyStatusService bodyStatusService;

    @Operation(summary = "Save BodyStatus", description = "신체정보 저장")
    @PostMapping("/{user-id}")
    public ResponseEntity<?> saveBodyStatus(
            @PathVariable("user-id") final String userId,
            @RequestBody BodyStatusRequest request){
        ResponseDto response = new ResponseDto(true, List.of(bodyStatusService.saveInfo(request, userId)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get BodyStatus", description = "신체정보 가져오기")
    @GetMapping("/{body-status-id}")
    public ResponseEntity<?> getBodyStatus(
            @PathVariable("body-status-id") final Long bodyStatusId) {
        ResponseDto response = new ResponseDto(true, List.of(bodyStatusService.getInfo(bodyStatusId)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get BodyStatus", description = "신체정보 가져오기")
    @GetMapping("/list/{user-id}")
    public ResponseEntity<?> getBodyStatusList(
            @PathVariable("user-id") final String userId
    ) {
        ResponseDto response = new ResponseDto(true, bodyStatusService.getInfoList(userId));
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Edit BodyStatus", description = "신체정보 수정")
    @PutMapping("/{body-status-id}")
    public ResponseEntity<?> editBodyStatus(
            @PathVariable("body-status-id") final Long bodyStatusId,
            @RequestBody BodyStatusRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(bodyStatusService.modifyInfo(bodyStatusId, request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete BodyStatus", description = "신체정보 삭제")
    @DeleteMapping("/{body-status-id}")
    public ResponseEntity<?> deleteBodyStatus(
            @PathVariable("body-status-id") final Long bodyStatusId) {
        ResponseDto response = new ResponseDto(true, List.of(bodyStatusService.deleteInfo(bodyStatusId)));
        return ResponseEntity.ok(response);
    }
}
