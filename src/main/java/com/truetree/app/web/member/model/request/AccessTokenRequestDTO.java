package com.truetree.app.web.member.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 *
 * 카카오 로그인 accessToken 을 받을 DTO
 *
 * @author pursue503
 */

@Getter
@NoArgsConstructor
public class AccessTokenRequestDTO {

    @NotEmpty(message = "Access Token 은 필수 값 입니다.")
    private String accessToken;

}
