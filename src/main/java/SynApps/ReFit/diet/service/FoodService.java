package synApps.refit.diet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.diet.dto.request.FoodRequest;
import synApps.refit.diet.entity.Food;
import synApps.refit.diet.entity.FoodReference;
import synApps.refit.diet.repository.FoodRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    @Transactional
    public Food saveFood(FoodRequest request) {
        Food food = Food.of(
                request.getFoodName(),
                FoodReference.of(request.getFoodReference()),
                request.getGramPerMeal(),
                request.getCalorie(),
                request.getCarbohydrate(),
                request.getProtein(),
                request.getFat(),
                request.getSugar(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        foodRepository.save(food);
        return food;
    }

    @Transactional
    public Food modifyFood(Long foodId, FoodRequest request) {
        Food food = foodRepository.findByFoodId(foodId);
        food.modifyFood(
                request.getFoodName(),
                FoodReference.of(request.getFoodReference()),
                request.getGramPerMeal(),
                request.getCalorie(),
                request.getCarbohydrate(),
                request.getProtein(),
                request.getFat(),
                request.getSugar(),
                LocalDateTime.now()
        );

        return food;
    }

    public Food getFood(Long foodId) {
        return foodRepository.findByFoodId(foodId);
    }

    public String deleteFood(Long foodId) {
        Food food = foodRepository.findByFoodId(foodId);
        foodRepository.delete(food);
        return "Delete successful";
    }
}
