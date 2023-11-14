package synApps.refit.exercise.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {
    @Id
    @GeneratedValue
    private Long exerciseId;

    @NotNull
    private String exerciseName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ExercisePart exercisePart;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ExerciseType exerciseType;

    private String exerciseReference;
    private String exerciseImageUrl;
    private Exercise(String exerciseName, ExercisePart exercisePart, ExerciseType exerciseType) {
        this.exerciseName = exerciseName;
        this.exercisePart = exercisePart;
        this.exerciseType = exerciseType;
    }

    private Exercise(String exerciseName, ExercisePart exercisePart, ExerciseType exerciseType, String exerciseReference, String exerciseImageUrl){
            this.exerciseName =exerciseName;
            this.exercisePart = exercisePart;
            this.exerciseType = exerciseType;
            this.exerciseReference =exerciseReference;
            this.exerciseImageUrl = exerciseImageUrl;
    }

    public static Exercise of(String exerciseName, ExercisePart exercisePart, ExerciseType exerciseType) {
        return new Exercise(exerciseName, exercisePart, exerciseType);
    }

    public static Exercise of(String exerciseName, ExercisePart exercisePart, ExerciseType exerciseType,String exerciseReference, String exerciseImageUrl) {
        return new Exercise(exerciseName, exercisePart, exerciseType,exerciseReference, exerciseImageUrl);
    }

    public void setReferenceAndImageUrl(String exerciseReference, String exerciseImageUrl) {
        this.exerciseReference =exerciseReference;
        this.exerciseImageUrl = exerciseImageUrl;
    }
}

