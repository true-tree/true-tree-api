package com.truetree.app.web.Game.controller;

import com.truetree.app.domain.coin.service.BitCoinService;
import com.truetree.app.domain.main.game.entity.GameRepository;
import com.truetree.app.domain.main.game.service.GameService;
import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.domain.main.member.service.oauth.MemberService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "게임 시작")
@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class GameController {

    private final GameService gameService;

    @Operation(summary = "게임 시작시 게임이 존재하면 false, 존재하지 않다면 게임 생성 후 true")
    @PostMapping("/create/game")
    public ResponseEntity<Boolean> createGame(@AuthenticationPrincipal User user){

        Long memberId = Long.valueOf(user.getUsername());

        if(gameService.isGameExist(memberId)) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        gameService.createGame(memberId);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}
