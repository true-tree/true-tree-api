package com.truetree.app.domain.member.service.oauth;

import com.truetree.app.domain.member.entity.Member;
import com.truetree.app.domain.member.entity.MemberAuthority;
import com.truetree.app.domain.member.entity.MemberAuthorityRepository;
import com.truetree.app.domain.member.entity.MemberRepository;
import com.truetree.app.domain.member.entity.enums.Authority;
import com.truetree.app.domain.member.service.oauth.kakao.KakaoCall;
import com.truetree.app.domain.member.vo.kakao.KakaoProfileVO;
import com.truetree.app.domain.member.vo.kakao.KakaoTokenVO;
import com.truetree.app.web.member.model.request.MemberSaveRequestDTOForKakao;
import com.truetree.app.web.member.model.response.TokenResponseDTO;
import com.truetree.common.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final JwtTokenUtil jwtTokenUtil;
    private final MemberRepository memberRepository;
    private final MemberAuthorityRepository memberAuthorityRepository;
    private final KakaoCall kakaoRequest;


    @Transactional(rollbackFor = RuntimeException.class)
    public TokenResponseDTO getGeneratedTokens(KakaoProfileVO profile) {

        Member member = memberRepository.findBySnsId(String.valueOf(profile.getId()))
                .orElse(profile.toEntity());

        memberRepository.save(member);

        MemberAuthority memberAuthority = MemberAuthority.builder()
                .memberId(member)
                .authority(Authority.USER)
                .build();

        memberAuthorityRepository.save(memberAuthority);


        return createToken(member.getId());
    }

    public KakaoProfileVO getKakaoProfile(String code) {
        KakaoTokenVO kakaoToken = kakaoRequest.getKakaoAccessToken(code);
        return kakaoRequest.getKakaoProfile(kakaoToken);
    }

    private TokenResponseDTO createToken(Long userId) {
        String accessToken = jwtTokenUtil.createAccessToken(userId);
        String refreshToken = jwtTokenUtil.createRefreshToken();

        return new TokenResponseDTO(accessToken, refreshToken);
    }


}
