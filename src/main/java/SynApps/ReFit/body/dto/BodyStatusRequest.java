package synApps.refit.body.dto;

import lombok.*;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class BodyStatusRequest {
    private String birth;
    private String gender;
    private float height;
    private float weight;
    private float sketeletallMuscleMass;
    private float bodyFatMass;
    private String goal;
}