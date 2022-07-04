package com.truetree.app.web.member.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;

/**
 *
 * Access , Refresh 토큰을 담아줄 DTO
 *
 * @author pursue503
 */

@RequiredArgsConstructor
@Getter
public class TokenResponseDTO {

    private final String accessToken;
    private final String refreshToken;

}
