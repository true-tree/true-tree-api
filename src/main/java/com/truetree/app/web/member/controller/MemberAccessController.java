package com.truetree.app.web.member.controller;

import com.truetree.app.domain.main.member.service.oauth.MemberService;
import com.truetree.app.domain.main.member.vo.kakao.KakaoProfileVO;
import com.truetree.app.web.member.model.response.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/v1")
@RequiredArgsConstructor
@RestController
public class MemberAccessController {

    private final MemberService memberService;

    @RequestMapping("/login/oauth2/code/kakao")
    public ResponseEntity<TokenResponseDTO> signUp(@RequestParam("code") String code) {

        log.info("asdasda");
        KakaoProfileVO kakaoProfile = memberService.getKakaoProfile(code);
        return new ResponseEntity<>(memberService.getGeneratedTokens(kakaoProfile), HttpStatus.OK);
    }

}
