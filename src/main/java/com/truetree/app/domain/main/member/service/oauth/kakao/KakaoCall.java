package com.truetree.app.domain.main.member.service.oauth.kakao;

import com.truetree.app.domain.main.member.vo.kakao.KakaoProfileVO;
import com.truetree.app.domain.main.member.vo.kakao.KakaoTokenVO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 카카오 서버로 요청을 도와줄 클래스
 *
 * @author pursue503
 *
 */

@Getter
@Component
public class KakaoCall {

    @Value("${kakao.authorization-grant-type}")
    private String grantType;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    public KakaoTokenVO getKakaoAccessToken(String code) {

        HttpHeaders header = new HttpHeaders();

        header.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", this.grantType);
        body.add("client_id", this.clientId);
        body.add("redirect_uri", this.redirectUri);
        body.add("code", code);
        // body.add("client_secret", this.clientSecret);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, header);
        RestTemplate rt = new RestTemplate();

        return rt.postForObject(
                "https://kauth.kakao.com/oauth/token",
                kakaoTokenRequest,
                KakaoTokenVO.class
        );
    }

    public KakaoProfileVO getKakaoProfile(KakaoTokenVO kakaoTokenVO) {

        final String authorization = "Bearer " + kakaoTokenVO.getAccess_token();

        HttpHeaders header = new HttpHeaders();

        header.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        header.add("Authorization", authorization);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        //body.add("client_secret", this.clientSecret);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, header);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(
                "https://kapi.kakao.com/v2/user/me",
                kakaoTokenRequest,
                KakaoProfileVO.class);
    }


}
