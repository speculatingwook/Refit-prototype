package synApps.refit.diet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class FoodRequest {
    private String foodName;
    private String foodReference;

    private float gramPerMeal;

    private float calorie;

    private float carbohydrate;

    private float protein;

    private float fat;

    private float sugar;
}
