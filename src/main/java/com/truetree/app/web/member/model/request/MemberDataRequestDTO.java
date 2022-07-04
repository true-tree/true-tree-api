package com.truetree.app.web.member.model.request;

import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.domain.main.member.entity.MemberAuthority;
import com.truetree.app.domain.main.member.entity.enums.SocialType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ComponentScan
public class MemberDataRequestDTO {

    private Long id;

    private String snsId;


    private String nickName;

    private SocialType socialType;

    private Integer money;

    private List<MemberAuthority> memberAuthorityList;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    public MemberDataRequestDTO(Long memberId, String snsId, String nickName, SocialType socialType, Integer money,
                                List<MemberAuthority> memberAuthorityList, LocalDateTime createAt,
                                LocalDateTime updateAt){
        this.id = memberId;
        this.snsId = snsId;
        this.nickName =nickName;
        this.socialType =socialType;
        this.money = money;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.memberAuthorityList = memberAuthorityList;
    }

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .snsId(snsId)
                .nickName(nickName)
                .socialType(socialType)
                .money(money)
                .createAt(updateAt)
                .build();
    }
}
