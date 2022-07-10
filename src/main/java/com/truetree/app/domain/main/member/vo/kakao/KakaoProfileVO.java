package com.truetree.app.domain.main.member.vo.kakao;

import com.truetree.app.domain.main.member.entity.enums.SocialType;
import com.truetree.app.domain.main.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoProfileVO {
    private Long id;
    private KakaoAccountVO kakao_account;

    public Member toEntity() {
        String time = String.valueOf(System.currentTimeMillis()).substring(8);
        return Member.builder()
                .snsId(String.valueOf(id))
                .nickName(time + "new")
//                .nickName("random")
                .socialType(SocialType.KAKAO)
                .money(1000_0000)
                .build();
    }

}
