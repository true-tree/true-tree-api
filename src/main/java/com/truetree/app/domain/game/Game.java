package com.truetree.app.domain.game;

import com.truetree.app.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(schema = "true_tree_main")
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member memberId;

    @Column(length = 55, nullable = false)
    private String coinName;

    @Column(nullable = false)
    private Integer startNumber;

    @Column(nullable = false)
    private Integer playGameCount;

}
