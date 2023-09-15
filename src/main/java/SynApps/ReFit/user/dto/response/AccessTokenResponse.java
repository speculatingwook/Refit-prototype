package synApps.refit.user.dto.response;

import synApps.refit.global.dto.ResponseHeader;

public class AccessTokenResponse {
    private String token;
    ResponseHeader header;

    public AccessTokenResponse(String token, ResponseHeader header) {
        this.token = token;
        this.header = header;
    }
}
