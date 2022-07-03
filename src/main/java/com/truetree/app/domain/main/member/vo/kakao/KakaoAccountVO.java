package com.truetree.app.domain.main.member.vo.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class KakaoAccountVO {
    private Profile profile;

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Profile {
        private String nickname;
    }
}
