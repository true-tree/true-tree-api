package com.truetree.app.web.Game.controller;

import com.truetree.app.domain.coin.service.BitCoinService;
import com.truetree.app.domain.main.game.service.GameService;
import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.domain.main.member.service.oauth.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class GameController {

    private final GameService gameService;
    private final MemberService memberService;
    private final BitCoinService bitCoinService;

    @PostMapping("/create/game")
    public ResponseEntity<Boolean> createGame(@AuthenticationPrincipal User user){

        Long memberId = Long.valueOf(user.getUsername());

        if(gameService.isGameExist(memberId)) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }


        Member member = memberService.getMemberData(memberId);

        long amountOfData = bitCoinService.getAmount();
        gameService.createGame(member,amountOfData);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }
}
