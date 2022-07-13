package com.truetree.app.domain.main.game.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {


    @Test
    @DisplayName("랜덤 startNumber ")
    public void randomNumber(){
        //given
        long gameDataAmount = 200_000;


        //when
        Integer startNumber = Math.toIntExact((long) (Math.random() * (gameDataAmount - 499)) + 101);

        //then
        for(int i=0;i<500_000;i++) {

            // 101 <= startNumber <= 199501;
            assertThat(startNumber).isLessThanOrEqualTo((int) (199_501));
            assertThat(startNumber).isGreaterThanOrEqualTo(101);
        }

    }

}