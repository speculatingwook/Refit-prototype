package synApps.refit.body.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import synApps.refit.user.oauth.entity.RoleType;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("GENDER_MALE" , 0),
    FEMALE("GENDER_FEMALE", 1),
    OTHER("OTHER", 2);

    private final String gender;
    private final int serialNumber;

    public static Gender of(String gender) {
        return Arrays.stream(Gender.values())
                .filter(r->r.getGender().equals(gender))
                .findAny()
                .orElse(OTHER);
    }
}
