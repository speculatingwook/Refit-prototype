package synApps.refit.exercise.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class RoutineRequest {
    private boolean isText;
    private String userInputRoutine;
}
