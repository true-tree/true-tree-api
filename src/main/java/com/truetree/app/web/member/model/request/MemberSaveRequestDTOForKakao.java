package com.truetree.app.web.member.model.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * 카카오 로그인용 DTO
 *
 */

@Getter
@RequiredArgsConstructor
public class MemberSaveRequestDTOForKakao {

    private final String code;
    private final String redirectUri;

}
