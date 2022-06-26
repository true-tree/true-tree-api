package com.truetree.app.domain.member.entity;

import com.truetree.app.domain.member.entity.Member;
import com.truetree.app.domain.member.entity.enums.Authority;
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
    @JoinColumn(nullable = false)
    private Member memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

}
