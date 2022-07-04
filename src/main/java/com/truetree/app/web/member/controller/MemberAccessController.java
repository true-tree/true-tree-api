package com.truetree.app.web.member.controller;

import com.truetree.app.domain.coin.dto.response.BitCoinResponseDTO;
import com.truetree.app.domain.coin.service.BitCoinService;
import com.truetree.app.domain.main.game.dto.GameResponseDTO;
import com.truetree.app.domain.main.game.service.GameService;
import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.domain.main.member.service.oauth.MemberService;
import com.truetree.app.domain.main.member.vo.kakao.KakaoProfileVO;
import com.truetree.app.web.member.model.request.AccessTokenRequestDTO;
import com.truetree.app.web.member.model.request.MemberDataRequestDTO;
import com.truetree.app.web.member.model.response.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/v1")
@RequiredArgsConstructor
@RestController
public class MemberAccessController {

    private final MemberService memberService;
    private final GameService gameService;
    private final BitCoinService bitCoinService;

    @RequestMapping("/login/oauth2/code/kakao")
    public ResponseEntity<TokenResponseDTO> signUp(@RequestParam("code") String code) {

        KakaoProfileVO kakaoProfile = memberService.getKakaoProfileByCode(code);
        return new ResponseEntity<>(memberService.getGeneratedTokens(kakaoProfile), HttpStatus.OK);
    }

    @PostMapping("/login/oauth2/code/kakao/access-token")
    public ResponseEntity<TokenResponseDTO> signUpForKakaoLoginByAccessToken(@RequestBody AccessTokenRequestDTO accessToken) {
        KakaoProfileVO kakaoProfileVO = memberService.getKakaoProfileByAccessToken(accessToken.getAccessToken());
        return new ResponseEntity<>(memberService.getGeneratedTokens(kakaoProfileVO), HttpStatus.OK);
    }

    @PostMapping("/create/game")
    public ResponseEntity<Boolean> createGame(@AuthenticationPrincipal User user){

        GameResponseDTO gameResponseDTO = gameService.isGameExist(Long.valueOf(user.getUsername()));

        if(gameResponseDTO == null){
            Member member = memberService.getMemberData(MemberDataRequestDTO.builder()
                    .memberId(Long.valueOf(user.getUsername()))
                    .snsId(user.getPassword())
                    .build());

            long amountOfData = bitCoinService.getAmount();
            Integer randomLevel = Math.toIntExact((long) (Math.random() * (amountOfData - 600)) + 101);
            gameService.createGame(member,randomLevel);

            return new ResponseEntity<>(true,HttpStatus.OK);
        }

        return new ResponseEntity<>(false,HttpStatus.OK);
    }
}
