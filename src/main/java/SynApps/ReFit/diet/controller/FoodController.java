package synApps.refit.diet.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.diet.dto.request.FoodRequest;
import synApps.refit.diet.service.FoodService;
import synApps.refit.global.dto.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @Operation(summary = "Save Food", description = "음식 정보 저장")
    @PostMapping()
    public ResponseEntity<?> saveFood(
            @RequestBody FoodRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(foodService.saveFood(request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Food", description = "음식 정보 가져오기")
    @GetMapping("/{food-id}")
    public ResponseEntity<?> getFood(
            @PathVariable("food-id") final Long foodId) {
        ResponseDto response = new ResponseDto(true, List.of(foodService.getFood(foodId)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Edit Food", description = "음식 정보 수정")
    @PutMapping("/{food-id}")
    public ResponseEntity<?> editFood(
            @PathVariable("food-id") final Long foodId,
            @RequestBody FoodRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(foodService.modifyFood(foodId, request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Food", description = "음식 정보 삭제")
    @DeleteMapping("/{food-id}")
    public ResponseEntity<?> deleteFood(
            @PathVariable("food-id") final Long foodId) {
        ResponseDto response = new ResponseDto(true, List.of(foodService.deleteFood(foodId)));
        return ResponseEntity.ok(response);
    }
}
