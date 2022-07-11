package com.truetree.app.web.member.controller;

import com.truetree.app.domain.coin.service.BitCoinService;
import com.truetree.app.domain.main.game.service.GameService;
import com.truetree.app.domain.main.member.service.oauth.MemberService;
import com.truetree.app.domain.main.member.vo.kakao.KakaoProfileVO;
import com.truetree.app.web.member.model.request.AccessTokenRequestDTO;
import com.truetree.app.web.member.model.response.TokenResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "회원가입")
@Slf4j
@RequestMapping("/v1")
@RequiredArgsConstructor
@RestController
public class MemberAccessController {

    private final MemberService memberService;

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
}
