package synApps.refit.global.dto;

import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class ResponseDto {
    private final boolean success;
    private final List<?> result;
}
