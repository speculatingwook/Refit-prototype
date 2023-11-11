package synApps.refit.exercise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExercisePart {
    BACK("BACK", 0),
    ARM("ARM", 1),
    OTHER("OTHER", 99);

    private final String part;
    private final int serialNumber;

    public static ExercisePart of(String partName) {
        return Arrays.stream(ExercisePart.values())
                .filter(r->r.getPart().equals(partName))
                .findAny()
                .orElse(OTHER);
    }
}
