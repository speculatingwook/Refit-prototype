package synApps.refit.diet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import synApps.refit.schedule.entity.Schedule;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DietRecord {
    @Id
    @GeneratedValue
    private Long dietRecordId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Schedule schedule;

    private MealTime mealTime;

    private LocalTime startAt;
    private LocalTime endAt;

    @NotNull
    private float dietCalorie;
    @NotNull
    private float dietProtein;
    @NotNull
    private float dietCarbohydrate;
    @NotNull
    private float dietSugar;
    @NotNull
    private float dietFat;
    private String userInputDiet;
    private boolean isText;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private LocalDateTime modifiedAt;

    private DietRecord(Schedule schedule,
                       MealTime mealTime,
                       LocalTime startAt,
                       LocalTime endAt,
                       float dietCalorie,
                       float dietProtein,
                       float dietCarbohydrate,
                       float dietSugar,
                       float dietFat,
                       String userInputDiet,
                       boolean isText,
                       LocalDateTime createdAt,
                       LocalDateTime modifiedAt) {
        this.schedule = schedule;
        this.mealTime = mealTime;
        this.startAt = startAt;
        this.endAt = endAt;
        this.dietCalorie = dietCalorie;
        this.dietProtein = dietProtein;
        this.dietCarbohydrate = dietCarbohydrate;
        this.dietSugar = dietSugar;
        this.dietFat = dietFat;
        this.userInputDiet = userInputDiet;
        this.isText = isText;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    private DietRecord(Schedule schedule,
                       MealTime mealTime,
                       LocalTime startAt,
                       LocalTime endAt,
                       float dietCalorie,
                       float dietProtein,
                       float dietCarbohydrate,
                       float dietSugar,
                       float dietFat,
                       LocalDateTime createdAt,
                       LocalDateTime modifiedAt) {
        this.schedule = schedule;
        this.mealTime = mealTime;
        this.startAt = startAt;
        this.endAt = endAt;
        this.dietCalorie = dietCalorie;
        this.dietProtein = dietProtein;
        this.dietCarbohydrate = dietCarbohydrate;
        this.dietSugar = dietSugar;
        this.dietFat = dietFat;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void modifyDietRecord(Schedule schedule,
                                 MealTime mealTime,
                                 LocalTime startAt,
                                 LocalTime endAt,
                                 float dietCalorie,
                                 float dietProtein,
                                 float dietCarbohydrate,
                                 float dietSugar,
                                 float dietFat,
                                 String userInputDiet,
                                 boolean isText,
                                 LocalDateTime createdAt,
                                 LocalDateTime modifiedAt) {
        this.schedule = schedule;
        this.mealTime = mealTime;
        this.startAt = startAt;
        this.endAt = endAt;
        this.dietCalorie = dietCalorie;
        this.dietProtein = dietProtein;
        this.dietCarbohydrate = dietCarbohydrate;
        this.dietSugar = dietSugar;
        this.dietFat = dietFat;
        this.userInputDiet = userInputDiet;
        this.isText = isText;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static DietRecord of(Schedule schedule,
                                MealTime mealTime,
                                LocalTime startAt,
                                LocalTime endAt,
                                float dietCalorie,
                                float dietProtein,
                                float dietCarbohydrate,
                                float dietSugar,
                                float dietFat,
                                String userInputDiet,
                                boolean isText,
                                LocalDateTime createdAt,
                                LocalDateTime modifiedAt) {
        return new DietRecord(schedule, mealTime, startAt, endAt, dietCalorie, dietProtein, dietCarbohydrate, dietSugar, dietFat, userInputDiet, isText, createdAt, modifiedAt);
    }
    public static DietRecord of(Schedule schedule,
                                MealTime mealTime,
                                LocalTime startAt,
                                LocalTime endAt,
                                float dietCalorie,
                                float dietProtein,
                                float dietCarbohydrate,
                                float dietSugar,
                                float dietFat,
                                LocalDateTime createdAt,
                                LocalDateTime modifiedAt) {
        return new DietRecord(schedule, mealTime, startAt, endAt, dietCalorie, dietProtein, dietCarbohydrate, dietSugar, dietFat, createdAt, modifiedAt);
    }


}
