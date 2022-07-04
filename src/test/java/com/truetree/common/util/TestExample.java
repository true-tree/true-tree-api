package com.truetree.common.util;

import com.truetree.app.domain.main.game.dto.GameResponseDTO;
import com.truetree.app.domain.main.game.entity.Game;
import com.truetree.app.domain.main.game.entity.GameRepository;
import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.domain.main.member.entity.MemberRepository;
import com.truetree.app.domain.main.member.entity.enums.SocialType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestExample {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GameRepository gameRepository;

    @Test
    void queryLogTest() {

        Member meber = memberRepository.save(
                Member.builder()
                        .snsId("sns_id")
                        .nickName("nickName")
                        .socialType(SocialType.KAKAO)
                        .money(1000)
                        .build()
        );


        Game game = gameRepository.save(
                Game.builder()
                        .memberId(meber)
                        .coinName("BTC")
                        .startNumber(100)
                        .playGameCount(0)
                        .build()
        );

        Game findGame = gameRepository.findByMemberId_Id(meber.getId())
                .orElseThrow();


        GameResponseDTO gameResponseDTO = new GameResponseDTO(findGame);

        System.out.println(gameResponseDTO.toString());
        System.out.println(gameResponseDTO.getMemberId().getId());




    }

}
