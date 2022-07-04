package com.truetree.app.domain.main.member.dto;

import com.truetree.app.domain.main.member.entity.MemberAuthority;
import com.truetree.app.domain.main.member.entity.enums.SocialType;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDataResponseDTO {

    private Long id;

    private String snsId;


    private String nickName;

    private SocialType socialType;

    private Integer money;

    private List<MemberAuthority> memberAuthorityList;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    public UserDataResponseDTO(Long id,String snsId,String nickName,SocialType socialType,Integer money, List<MemberAuthority> memberAuthorityList, LocalDateTime createAt , LocalDateTime updateAt){

        this.id = id;
        this.snsId = snsId;
        this.nickName =nickName;
        this.socialType = socialType;
        this.money = money;
        this.memberAuthorityList = memberAuthorityList;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
