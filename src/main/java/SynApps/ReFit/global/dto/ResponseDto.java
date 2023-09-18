package synApps.refit.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
public class ResponseDto {
    private final boolean success;
    private final List<?> result;
}