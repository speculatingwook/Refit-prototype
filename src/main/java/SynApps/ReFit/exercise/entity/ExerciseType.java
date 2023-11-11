package synApps.refit.exercise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExerciseType {
    BIKE("BIKE", 0),
    FITNESS("FITNESS", 1),
    OTHER("OTHER", 2);
    private final String type;
    private final int serialNumber;

    public static ExerciseType of(String typeName) {
        return Arrays.stream(ExerciseType.values())
                .filter(r->r.getType().equals(typeName))
                .findAny()
                .orElse(OTHER);
    }
}
