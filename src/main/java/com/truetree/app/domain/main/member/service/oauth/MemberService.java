package com.truetree.app.domain.main.member.service.oauth;

import com.truetree.app.domain.main.member.entity.MemberAuthority;
import com.truetree.app.domain.main.member.entity.MemberAuthorityRepository;
import com.truetree.app.domain.main.member.entity.enums.Authority;
import com.truetree.app.domain.main.member.service.oauth.kakao.KakaoCall;
import com.truetree.app.domain.main.member.vo.kakao.KakaoTokenVO;
import com.truetree.app.domain.main.member.entity.Member;
import com.truetree.app.domain.main.member.entity.MemberRepository;
import com.truetree.app.domain.main.member.vo.kakao.KakaoProfileVO;
import com.truetree.app.web.member.model.request.MemberDataRequestDTO;
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

    public KakaoProfileVO getKakaoProfileByCode(String code) {
        KakaoTokenVO kakaoToken = kakaoRequest.getKakaoAccessToken(code);
        return kakaoRequest.getKakaoProfile(kakaoToken.getAccess_token());
    }
    
    public KakaoProfileVO getKakaoProfileByAccessToken(String accessToken) {
        return kakaoRequest.getKakaoProfile(accessToken);
    }

    private TokenResponseDTO createToken(Long userId) {
        String accessToken = jwtTokenUtil.createAccessToken(userId);
        String refreshToken = jwtTokenUtil.createRefreshToken();

        return new TokenResponseDTO(accessToken, refreshToken);
    }

    public Member getMemberData(MemberDataRequestDTO requestDTO){
        Member member = memberRepository.findById(requestDTO.getId()).get();

        return member;
    }

}
