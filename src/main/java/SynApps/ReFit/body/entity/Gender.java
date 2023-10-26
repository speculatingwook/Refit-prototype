package synApps.refit.body.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import synApps.refit.user.oauth.entity.RoleType;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("MALE" , 0),
    FEMALE("FEMALE", 1),
    OTHER("OTHER", 2);

    private final String gender;
    private final int serialNumber;

    /**
     *
     * @param gender String 입력
     * @return Gender enum으로 변환
     */
    public static Gender of(String gender) {
        return Arrays.stream(Gender.values())
                .filter(r->r.getGender().equals(gender))
                .findAny()
                .orElse(OTHER);
    }
}
