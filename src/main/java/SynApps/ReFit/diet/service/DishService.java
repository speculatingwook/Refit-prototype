package synApps.refit.diet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.diet.dto.request.DishRequest;
import synApps.refit.diet.entity.DietRecord;
import synApps.refit.diet.entity.Dish;
import synApps.refit.diet.repository.DietRecordRepository;
import synApps.refit.diet.repository.DishRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DietRecordRepository dietRecordRepository;

    @Transactional
    public Dish saveDish(Long dietRecordId, DishRequest request) {
        DietRecord dietRecord = dietRecordRepository.findByDietRecordId(dietRecordId);

        Dish dish = Dish.of(
                dietRecord,
                request.getAmount(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        dishRepository.save(dish);
        return dish;
    }

    @Transactional
    public Dish modifyDish(Long dishId, DishRequest request) {
        Dish dish = dishRepository.findByDishId(dishId);
        dish.modifyDish(request.getAmount(), LocalDateTime.now());
        return dish;
    }

    public Dish getDish(Long dishId) {
        return dishRepository.findByDishId(dishId);
    }

    public List<Dish> getDishList(Long recordId) {
        DietRecord dietRecord = dietRecordRepository.findByDietRecordId(recordId);
        return dietRecord.getDishList();
    }

    public String deleteDish(Long dishId) {
        Dish dish = dishRepository.findByDishId(dishId);
        dishRepository.delete(dish);
        return "Delete successful";
    }
}
