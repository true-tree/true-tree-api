package com.truetree.app.domain.member.entity;

import com.truetree.app.domain.member.entity.enums.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(
        schema = "true_tree_main",
        indexes = {
                @Index(unique = true, name = "UQ_member_1", columnList = "nickName") // 필드명을 적어줘야함
        },
        uniqueConstraints = {
              @UniqueConstraint(name = "UQ_member_1",columnNames = {"nickName"}) // 필드명을 적어줘야함
        }
)
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String snsId;

    @Column(length = 30, nullable = false, unique = true)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType socialType;

    @Column(nullable = false)
    private Integer money;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "memberId")
    private List<MemberAuthority> memberAuthorityList = new ArrayList<>();

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;


    @Builder
    public Member(String snsId, String nickName, SocialType socialType, Integer money, List<MemberAuthority> memberAuthorityList, LocalDateTime createAt, LocalDateTime updateAt) {
        this.snsId = snsId;
        this.nickName = nickName;
        this.socialType = socialType;
        this.money = money;
        this.memberAuthorityList = memberAuthorityList;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public List<GrantedAuthority> getMemberAuthority() {
        return this.memberAuthorityList
                .stream()
                .map(entity -> new SimpleGrantedAuthority(entity.getAuthority().name()))
                .collect(Collectors.toList());
    }

}
