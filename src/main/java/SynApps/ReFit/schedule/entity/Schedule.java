package synApps.refit.schedule.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import synApps.refit.exercise.entity.ExerciseRecord;
import synApps.refit.user.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Id
    @GeneratedValue
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonIgnore
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "exerciseRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<ExerciseRecord> exerciseRecordList = new ArrayList<>();

    @NotNull
    private LocalDate date;

    private float totalCalorie;
    private float totalProtein;
    private float totalCarbohydrates;
    private float totalSugar;
    private float totalFat;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime modifiedAt;

    private Schedule(
            User user,
            LocalDate date,
            float totalCalorie,
            float totalProtein,
            float totalCarbohydrates,
            float totalSugar,
            float totalFat,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt) {
        this.user = user;
        this.date = date;
        this.totalCalorie = totalCalorie;
        this.totalProtein = totalProtein;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalSugar = totalSugar;
        this.totalFat = totalFat;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Schedule of(User user,
                              LocalDate date,
                              float totalCalorie,
                              float totalProtein,
                              float totalCarbohydrates,
                              float totalSugar,
                              float totalFat,
                              LocalDateTime createdAt,
                              LocalDateTime modifiedAt) {
        return new Schedule(user, date, totalCalorie, totalProtein, totalCarbohydrates, totalSugar, totalFat, createdAt, modifiedAt);
    }

    public void modifySchedule(LocalDate date,
                               float totalCalorie,
                               float totalProtein,
                               float totalCarbohydrates,
                               float totalSugar,
                               float totalFat,
                               LocalDateTime modifiedAt) {
        this.date = date;
        this.totalCalorie = totalCalorie;
        this.totalProtein = totalProtein;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalSugar = totalSugar;
        this.totalFat = totalFat;
        this.modifiedAt = modifiedAt;
    }

}
