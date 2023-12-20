package synApps.refit.level.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.global.dto.ResponseDto;
import synApps.refit.level.service.LevelService;
import synApps.refit.level.entity.Level;

import java.util.List;

@RestController
@RequestMapping("/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @Operation(summary = "Get User Level", description = "사용자 레벨 정보 가져오기")
    @GetMapping()
    public ResponseEntity<?> getUserLevel() {
        Level level = levelService.getLevel();
        ResponseDto response = new ResponseDto(true, List.of(level));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Save User Level", description = "사용자 레벨 저장")
    @PostMapping()
    public ResponseEntity<?> saveUserLevel(@RequestParam Integer exp) {
        Level level = levelService.saveLevel(exp);
        ResponseDto response = new ResponseDto(true, List.of(level));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Initialize User Level", description = "사용자 레벨 초기화")
    @PostMapping("/init")
    public ResponseEntity<?> initializeUserLevel() {
        Level level = levelService.initLevel();
        ResponseDto response = new ResponseDto(true, List.of(level));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update User Level", description = "사용자 레벨 업데이트")
    @PutMapping()
    public ResponseEntity<?> updateUserLevel(@RequestParam Integer exp) {
        Level level = levelService.updateLevel(exp);
        ResponseDto response = new ResponseDto(true, List.of(level));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete User Level", description = "사용자 레벨 삭제")
    @DeleteMapping()
    public ResponseEntity<?> deleteUserLevel() {
        levelService.deleteLevel();
        return ResponseEntity.ok(new ResponseDto(true, List.of("Level deleted successfully")));
    }
}
