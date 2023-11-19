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
public class RoutineSet {
    @Id
    @GeneratedValue
    private Long routineSetId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Routine routine;

    @NotNull
    private int setOrder;

    @NotNull
    private float weight;

    @NotNull
    private int repeat;

    @NotNull
    private boolean isDone;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime modifiedAt;

    private RoutineSet(Routine routine, int setOrder, float weight, int repeat, boolean isDone, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.routine = routine;
        this.setOrder = setOrder;
        this.weight = weight;
        this.repeat = repeat;
        this.isDone = isDone;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static RoutineSet of(Routine routine, int setOrder, float weight, int repeat, boolean isDone, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new RoutineSet(routine, setOrder, weight, repeat, isDone, createdAt, modifiedAt);
    }

    public void modifyInfo(int setOrder, float weight, int repeat, boolean isDone, LocalDateTime modifiedAt) {
        this.setOrder = setOrder;
        this.weight = weight;
        this.repeat = repeat;
        this.isDone = isDone;
        this.modifiedAt = modifiedAt;
    }

    public void done() {
        this.isDone = !isDone;
    }

}
