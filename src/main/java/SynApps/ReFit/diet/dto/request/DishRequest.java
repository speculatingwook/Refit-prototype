package synApps.refit.diet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DishRequest {
    private float amount;
}
