package com.truetree.app.domain.main.game.service;

import com.truetree.app.domain.coin.service.BitCoinService;
import com.truetree.app.domain.main.game.dto.GameResponseDTO;
import com.truetree.app.domain.main.game.entity.Game;
import com.truetree.app.domain.main.game.entity.GameRepository;
import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.domain.main.member.service.oauth.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final MemberService memberService;
    private final BitCoinService bitCoinService;

    @Transactional(readOnly = true)
    public boolean isGameExist(Long memberId){

        return gameRepository.existsByMemberId(memberId);

    }

    @Transactional
    public void createGame(Long memberId){
        long gameDataAmount = bitCoinService.getAmount();

        //101 <= startNumber <= (데이터의 총 개수-499)
        Integer startNumber = Math.toIntExact((long) (Math.random() * (gameDataAmount - 499)) + 101);

        Member member = memberService.getMemberData(memberId);

        gameRepository.save(
                Game.builder()
                        .member(member)
                        .coinName("BTC")
                        .startNumber(startNumber)
                        .playGameCount(0)
                        .build());

    }

}
