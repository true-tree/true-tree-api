package com.truetree.config.security.jwt;

import com.truetree.app.domain.member.entity.MemberRepository;
import com.truetree.common.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberRepository memberRepository;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(new JwtFilter(jwtTokenUtil, memberRepository), UsernamePasswordAuthenticationFilter.class);
    }
}
