package com.truetree.app.domain.main.game.service;

import com.truetree.app.domain.main.game.dto.GameResponseDTO;
import com.truetree.app.domain.main.game.entity.Game;
import com.truetree.app.domain.main.game.entity.GameRepository;
import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.web.member.model.request.MemberDataRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameResponseDTO isGameExist(Long memberId){
        Optional<Game> game = gameRepository.findByMemberId_Id(memberId);

        if(game.isPresent())
            return new GameResponseDTO(game.get());

        return null;
    }

    public void createGame(Member member,Integer startNumber){

        gameRepository.save(
                Game.builder()
                        .memberId(member)
                        .coinName("BTC")
                        .startNumber(startNumber)
                        .playGameCount(0)
                        .build());

    }

}
