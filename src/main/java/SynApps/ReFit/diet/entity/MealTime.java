package synApps.refit.diet.entity;

import lombok.Getter;
import synApps.refit.exercise.entity.ExercisePart;

import java.util.Arrays;

@Getter
public enum MealTime {
    BREAKFAST("아침",0),
    LUNCH("점심", 1),
    DINNER("저녁", 2),
    SNACK("간식", 3),
    LATE_NIGHT_SNACK("야식", 4),
    OTHER("기타", 99);
    private final String korean;
    private final int serialNumber;

    MealTime(String korean, int serialNumber) {
        this.korean = korean;
        this.serialNumber = serialNumber;
    }

    public static MealTime of(String korean) {
        return Arrays.stream(MealTime.values())
                .filter(r->r.getKorean().equals(korean))
                .findAny()
                .orElse(OTHER);
    }
}
