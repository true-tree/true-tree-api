package com.truetree.config.security;

import com.truetree.app.domain.main.member.entity.MemberRepository;
import com.truetree.common.jwt.JwtTokenUtil;
import com.truetree.config.security.jwt.JwtFilterConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;


/**
 *
 * Spring Security Config
 * Lambda DSL 사용
 *
 * @author pursue503
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberRepository memberRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.apply(new JwtFilterConfig(jwtTokenUtil, memberRepository));

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequest ->
                        authorizeRequest.antMatchers("/**").permitAll()
                )
                .headers(headers -> headers.frameOptions().disable())
                .formLogin(formLogin -> formLogin.disable())
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

}
