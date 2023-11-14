package synApps.refit.diet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DietRecordRequest {
    private String mealTime;
    private String startAt;
    private String endAt;
    private float dietCalorie;
    private float dietProtein;
    private float dietCarbohydrate;
    private float dietSugar;
    private float dietFat;
    private String userInputDiet;
    private boolean isText;
}
