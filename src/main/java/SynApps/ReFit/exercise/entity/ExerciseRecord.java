package synApps.refit.exercise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import synApps.refit.schedule.entity.Schedule;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseRecord {
    @Id
    @GeneratedValue
    private Long exerciseRecordId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Schedule schedule;

    private LocalTime startAt;
    private LocalTime endAt;

    private ExerciseRecord(Schedule schedule, LocalTime startAt, LocalTime endAt) {
        this.schedule = schedule;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static ExerciseRecord of(Schedule schedule, LocalTime startAt, LocalTime endAt) {
        return new ExerciseRecord(schedule, startAt, endAt);
    }

    public void modifyTime(LocalTime startAt, LocalTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void modifySchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
