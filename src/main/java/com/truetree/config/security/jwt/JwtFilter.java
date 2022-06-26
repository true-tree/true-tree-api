package com.truetree.config.security.jwt;

import com.truetree.app.domain.member.entity.Member;
import com.truetree.app.domain.member.entity.MemberRepository;
import com.truetree.app.domain.member.exception.MemberExceptionMessage;
import com.truetree.common.jwt.JwtTokenUtil;
import com.truetree.config.security.jwt.enums.AuthorizationType;
import com.truetree.exception.MemberUnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = getAccessToken(request);

        if (accessToken != null) {

            Long id = jwtTokenUtil.verifyAccessToken(accessToken);

            Authentication authentication = getAuthentication(id);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        return request.getHeader(AuthorizationType.BEARER.getType());
    }

    private Authentication getAuthentication(Long id) {
        Member member = memberRepository.findByMemberLoginData(id)
                .orElseThrow(() -> new MemberUnauthorizedException(MemberExceptionMessage.MEMBER_UNAUTHORIZED));

        List<GrantedAuthority> grantedAuthorityList = member.getMemberAuthority();

        UserDetails userDetails = new User(member.getId().toString(), member.getSnsId(), grantedAuthorityList);
        return new UsernamePasswordAuthenticationToken(userDetails, "", grantedAuthorityList);
    }

}
