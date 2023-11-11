package synApps.refit.exercise.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class RoutineSetRequest {
    private int setOrder;
    private float weight;
    private int repeat;
    private boolean isDone;
}
