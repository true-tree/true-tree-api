package com.truetree.exception;

import com.truetree.exception.common.ErrorTemplate;
import lombok.Getter;

/**
 *
 * 회원의 id가 존재하지 않을 때 사용하는 exception
 *
 * @author pursue503
 */

@Getter
public class MemberUnauthorizedException extends RuntimeException {

    public MemberUnauthorizedException(ErrorTemplate errorTemplate) {
        super(errorTemplate.getMessage());
    }

}
