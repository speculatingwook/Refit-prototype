package synApps.refit.exercise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine {
    @Id
    @GeneratedValue
    private Long routineId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonIgnore
    private ExerciseRecord exerciseRecord;


    @NotNull
    private boolean isText;

    @NotNull
    private String userInputRoutine;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime modifiedAt;

    private Routine(ExerciseRecord exerciseRecord, boolean isText, String userInputRoutine, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.exerciseRecord = exerciseRecord;
        this.isText = isText;
        this.userInputRoutine = userInputRoutine;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Routine of(ExerciseRecord exerciseRecord, boolean isText, String userInputRoutine, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new Routine(exerciseRecord, isText, userInputRoutine, createdAt, modifiedAt);
    }

    public void modifyUserInputRoutine(String userInputRoutine, LocalDateTime modifiedAt) {
        this.userInputRoutine = userInputRoutine;
        this.modifiedAt = modifiedAt;
    }

}
