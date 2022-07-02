package com.truetree.app.domain.game;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class GameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Integer rateOfReturn;

    @Column(nullable = false)
    private Integer startAmount;

    @Column(nullable = false)
    private Integer endAmount;

}
