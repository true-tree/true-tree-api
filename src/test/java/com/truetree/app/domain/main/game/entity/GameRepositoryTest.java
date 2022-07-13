package com.truetree.app.domain.main.game.entity;

import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.domain.main.member.entity.MemberRepository;
import com.truetree.app.domain.main.member.entity.enums.SocialType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    MemberRepository memberRepository;


    @BeforeEach
    void setup(){

        Member member = Member.builder()
                .snsId("21312412312")
                .nickName("1231232")
                .socialType(SocialType.KAKAO)
                .money(10_000_000)
                .build();

        Game game = Game.builder()
                .member(member)
                .coinName("BTC")
                .startNumber(0)
                .playGameCount(0)
                .build();

        memberRepository.save(member);
        gameRepository.save(game);
    }

    @Test
    void existsByMemberId() {

        //given
        Long signUpedUser = 1L;
        Long unsignUpedUser = 2L;

        //when
        boolean expectedTrue = gameRepository.existsByMemberId(signUpedUser);
        boolean expectedFalse = gameRepository.existsByMemberId(unsignUpedUser);

        //then
        assertThat(expectedTrue).isEqualTo(true);
        assertThat(expectedFalse).isEqualTo(false);

    }

}