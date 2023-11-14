package synApps.refit.exercise.dto.request;

import lombok.*;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ExerciseRequest {
    private String exerciseName;
    private String exercisePart;
    private String exerciseType;
    private String exerciseReference;
    private String exerciseImageUrl;
}