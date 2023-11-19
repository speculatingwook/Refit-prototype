package synApps.refit.diet.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import synApps.refit.diet.dto.request.DishRequest;
import synApps.refit.diet.service.DishService;
import synApps.refit.global.dto.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @Operation(summary = "Save Dish", description = "식사 정보 저장")
    @PostMapping("/{diet-record-id}")
    public ResponseEntity<?> saveDish(
            @PathVariable("diet-record-id") final Long dietRecordId,
            @RequestBody DishRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(dishService.saveDish(dietRecordId, request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Dish", description = "식사 정보 가져오기")
    @GetMapping("/{dish-id}")
    public ResponseEntity<?> getDish(
            @PathVariable("dish-id") final Long dishId) {
        ResponseDto response = new ResponseDto(true, List.of(dishService.getDish(dishId)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Dish List", description = "식사 정보 목록 가져오기")
    @GetMapping("/list/{record-id}")
    public ResponseEntity<?> getDishList(
            @PathVariable("record-id") final Long recordId) {
        ResponseDto response = new ResponseDto(true, dishService.getDishList(recordId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Edit Dish", description = "식사 정보 수정")
    @PutMapping("/{dish-id}")
    public ResponseEntity<?> editDish(
            @PathVariable("dish-id") final Long dishId,
            @RequestBody DishRequest request) {
        ResponseDto response = new ResponseDto(true, List.of(dishService.modifyDish(dishId, request)));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Dish", description = "식사 정보 삭제")
    @DeleteMapping("/{dish-id}")
    public ResponseEntity<?> deleteDish(
            @PathVariable("dish-id") final Long dishId) {
        ResponseDto response = new ResponseDto(true, List.of(dishService.deleteDish(dishId)));
        return ResponseEntity.ok(response);
    }
}
