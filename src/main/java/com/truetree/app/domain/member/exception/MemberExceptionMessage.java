package com.truetree.app.domain.member.exception;

import com.truetree.exception.common.ErrorTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberExceptionMessage implements ErrorTemplate {

    MEMBER_UNAUTHORIZED("로그인 하지 않았거나 존재하지 않는 회원 입니다.")
    ;

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}