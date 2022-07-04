package com.truetree.app.domain.main.game.dto;

import com.truetree.app.domain.main.game.entity.Game;
import com.truetree.app.domain.main.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameResponseDTO {

    private Long id;

    private Member memberId;

    private String coinName;

    private Integer startNumber;

    private Integer playGameCount;


    public GameResponseDTO(Game game){
        this.id = game.getId();
        this.memberId = game.getMemberId();
        this.coinName = game.getCoinName();
        this.startNumber = game.getStartNumber();
        this.playGameCount = game.getStartNumber();
    }
}
