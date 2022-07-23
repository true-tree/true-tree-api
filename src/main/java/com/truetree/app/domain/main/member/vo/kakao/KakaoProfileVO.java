package com.truetree.app.domain.main.member.vo.kakao;

import com.truetree.app.domain.main.member.entity.MemberAuthority;
import com.truetree.app.domain.main.member.entity.enums.Authority;
import com.truetree.app.domain.main.member.entity.enums.SocialType;
import com.truetree.app.domain.main.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoProfileVO {
    private Long id;
    private KakaoAccountVO kakao_account;

    public Member toEntity() {
        String time = String.valueOf(System.currentTimeMillis()).substring(8);
        Member member = Member.builder()
                .snsId(String.valueOf(id))
                .nickName(time + "new")
//                .nickName("random")
                .memberAuthorityList(new ArrayList<>())
                .socialType(SocialType.KAKAO)
                .money(1000_0000)
                .build();

        // 권한 초기화
        member.initMemberAuthority();
        return member;
    }

}
