package synApps.refit.diet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import synApps.refit.exercise.entity.Exercise;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish {
    @Id
    @GeneratedValue
    private Long dishId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foodId", unique = true)
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private DietRecord dietRecord;

    private float amount;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime modifiedAt;

    private Dish(DietRecord dietRecord,
                 float amount,
                 LocalDateTime createdAt,
                 LocalDateTime modifiedAt) {
        this.dietRecord = dietRecord;
        this.amount = amount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void modifyDish(float amount, LocalDateTime modifiedAt) {
        this.amount = amount;
        this.modifiedAt = modifiedAt;
    }

    public static Dish of(DietRecord dietRecord,
                          float amount,
                          LocalDateTime createdAt,
                          LocalDateTime modifiedAt) {
        return new Dish(dietRecord, amount, createdAt, modifiedAt);
    }
}
