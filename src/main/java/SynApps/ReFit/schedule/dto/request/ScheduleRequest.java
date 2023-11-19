package synApps.refit.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    String date;
    float totalCalorie;
    float totalProtein;
    float totalCarbohydrates;
    float totalSugar;
    float totalFat;
}
