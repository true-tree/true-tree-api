package com.truetree.app.domain.member.vo.kakao;

import com.truetree.app.domain.member.entity.Member;
import com.truetree.app.domain.member.entity.enums.SocialType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class KakaoProfileVO {
    private Long id;
    private KakaoAccountVO kakao_account;

    public Member toEntity() {
        String time = String.valueOf(System.currentTimeMillis()).substring(8);
        return Member.builder()
                .snsId(String.valueOf(id))
//                .nickName(kakao_account.getProfile().getNickname() + "_" + time)
                .nickName("random")
                .socialType(SocialType.KAKAO)
                .money(1000_0000)
                .build();
    }

}
