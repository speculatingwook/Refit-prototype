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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "routineId", unique = true)
    private Routine routine;

    @NotNull
    private String exerciseName;

    @NotNull
    private ExercisePart exercisePart;

    @NotNull
    private ExerciseType exerciseType;

    private String exerciseReference;
    private String exerciseImageUrl;
    private Exercise(Routine routine, String exerciseName, ExercisePart exercisePart, ExerciseType exerciseType) {
        this.routine = routine;
        this.exerciseName = exerciseName;
        this.exercisePart = exercisePart;
        this.exerciseType = exerciseType;
    }

    private Exercise(Routine routine, String exerciseName, ExercisePart exercisePart, ExerciseType exerciseType, String exerciseReference, String exerciseImageUrl){
            this.routine = routine;
            this.exerciseName =exerciseName;
            this.exercisePart = exercisePart;
            this.exerciseType = exerciseType;
            this.exerciseReference =exerciseReference;
            this.exerciseImageUrl = exerciseImageUrl;
    }

    public static Exercise of(Routine routine, String exerciseName, ExercisePart exercisePart, ExerciseType exerciseType) {
        return new Exercise(routine, exerciseName, exercisePart, exerciseType);
    }

    public static Exercise of(Routine routine, String exerciseName, ExercisePart exercisePart, ExerciseType exerciseType,String exerciseReference, String exerciseImageUrl) {
        return new Exercise(routine, exerciseName, exercisePart, exerciseType,exerciseReference, exerciseImageUrl);
    }

    public void setPartType(String exerciseReference, String exerciseImageUrl) {
        this.exerciseReference =exerciseReference;
        this.exerciseImageUrl = exerciseImageUrl;
    }
}

