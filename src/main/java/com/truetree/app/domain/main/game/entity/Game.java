package com.truetree.app.domain.main.game.entity;

import com.truetree.app.domain.main.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @Column(length = 55, nullable = false)
    private String coinName;

    @Column(nullable = false)
    private Integer startNumber;

    @Column(nullable = false)
    private Integer playGameCount;

    @Builder
    public Game(Member member, String coinName, Integer startNumber, Integer playGameCount){
        this.member = member;
        this.coinName = coinName;
        this.startNumber = startNumber;
        this.playGameCount = playGameCount;
    }

}
