package com.truetree.app.domain.main.game.service;

import com.truetree.app.domain.main.game.dto.GameResponseDTO;
import com.truetree.app.domain.main.game.entity.Game;
import com.truetree.app.domain.main.game.entity.GameRepository;
import com.truetree.app.domain.main.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public boolean isGameExist(Long memberId){

        if(gameRepository.existByMemberId(memberId))
            return true;

        return false;
    }

    @Transactional
    public void createGame(Member member,long gameDataAmount){

        Integer startNumber = Math.toIntExact((long) (Math.random() * (gameDataAmount - 500)) + 101);

        gameRepository.save(
                Game.builder()
                        .member(member)
                        .coinName("BTC")
                        .startNumber(startNumber)
                        .playGameCount(0)
                        .build());

    }

}
