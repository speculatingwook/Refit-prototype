package synApps.refit.body.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import synApps.refit.user.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BodyStatus {
    @Id
    @GeneratedValue
    private Long bodyStatusId;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonIgnore
    private User user;

    @NotNull
    private LocalDateTime birth;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    private float weight;

    @NotNull
    private float height;


    private float skeletalMuscleMass;

    private float bodyFatMass;

    @NotNull
    private String goal;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime modifiedAt;

    private BodyStatus(User user, LocalDateTime birth, Gender gender, float weight, float height, String goal, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.user = user;
        this.birth = birth;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    private BodyStatus(User user, LocalDateTime birth, Gender gender, float weight, float height,float skeletalMuscleMass, float bodyFatMass, String goal, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.user = user;
        this.birth = birth;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.skeletalMuscleMass = skeletalMuscleMass;
        this.bodyFatMass = bodyFatMass;
        this.goal = goal;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static BodyStatus of(User user, LocalDateTime birth, Gender gender, float weight, float height, String goal, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new BodyStatus(user, birth, gender, weight, height, goal, createdAt, modifiedAt);
    }
    public static BodyStatus of(User user, LocalDateTime birth, Gender gender, float weight, float height,float skeletalMuscleMass, float bodyFatMass, String goal, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new BodyStatus(user, birth, gender, weight, height, skeletalMuscleMass, bodyFatMass, goal, createdAt, modifiedAt);
    }

    public void modifyBodyStatus(LocalDateTime birth, Gender gender, float weight, float height,float skeletalMuscleMass, float bodyFatMass, String goal, LocalDateTime modifiedAt) {
        this.birth = birth;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.skeletalMuscleMass = skeletalMuscleMass;
        this.bodyFatMass = bodyFatMass;
        this.goal = goal;
        this.modifiedAt = modifiedAt;
    }
}