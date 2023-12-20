package synApps.refit.level.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import synApps.refit.user.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private User user;

    @NotNull
    @Column(nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer level;

    @NotNull
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer exp;

    private Level(User user,Integer exp) {
        LevelInfo levelInfo = new LevelInfo(exp);
        this.user = user;
        this.exp = exp;
        this.level = levelInfo.getLevel();
    }

    private Level(Integer exp) {
        calculateLevel(exp);
        this.exp = exp;
    }

    public static Level of(User user, Integer exp) {
        return new Level(user, exp);
    }

    public static Level of() {
        return new Level(0);
    }


    public static Level newLevel(User user) {
        return new Level(user, 0);
    }

    public void calculateLevel(Integer exp) {
        LevelInfo levelInfo = new LevelInfo(exp);
        this.exp = exp;
        this.level = levelInfo.getLevel();
    }
}
