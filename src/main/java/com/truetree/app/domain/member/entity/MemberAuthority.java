package com.truetree.app.domain.member.entity;

import com.truetree.app.domain.member.entity.enums.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(schema = "true_tree_main")
@NoArgsConstructor
@Entity
public class MemberAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

    @Builder
    public MemberAuthority(Member memberId, Authority authority) {
        this.memberId = memberId;
        this.authority = authority;
    }
}
