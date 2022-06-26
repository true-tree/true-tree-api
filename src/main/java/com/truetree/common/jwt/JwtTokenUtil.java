package com.truetree.common.jwt;

import com.truetree.config.security.jwt.enums.AuthorizationType;
import com.truetree.exception.jwt.JwtCustomException;
import com.truetree.exception.jwt.JwtError;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private final int accessTokenExpirationTime;
    private final int refreshTokenExpirationTime;
    private final Key secretKey;
    private final String claimKey;
    private final String issuer;
    private final String subject;

    public JwtTokenUtil(
            @Value("${true.tree.jwt.access-token-expiration-time}")
            Integer accessTokenExpirationTime,
            @Value("${true.tree.jwt.refresh-token-expiration-time}")
            Integer refreshTokenExpirationTime,
            @Value("${true.tree.jwt.secret-key}")
            String secretKey,
            @Value("${true.tree.jwt.claim-key}")
            String claimKey,
            @Value("${true.tree.jwt.issuer}")
            String issuer,
            @Value("${true.tree.jwt.subject}")
            String subject
    ) {
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.claimKey = claimKey;
        this.issuer = issuer;
        this.subject = subject;
    }

    public String createAccessToken(Long memberId) {

        long now = (new Date()).getTime();
        Date accessTokenExpiresDate = new Date(now + accessTokenExpirationTime);

        return Jwts.builder()
                .claim(claimKey, memberId)
                .setIssuer(issuer)
                .setSubject(subject)
                .setExpiration(accessTokenExpiresDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken() {

        long now = (new Date()).getTime();

        Date refreshTokenExpiresDate = new Date(now + refreshTokenExpirationTime);

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .setExpiration(refreshTokenExpiresDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long verifyAccessToken(String accessToken) {
        return Long.valueOf(decodeAccessToken(accessToken.replace(AuthorizationType.BEARER.getType(), "").trim()).get(claimKey)
                .toString());
    }

    public void verifyRefreshToken(String refreshToken) {
        decodeRefreshToken(refreshToken);
    }

    private Claims decodeAccessToken(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .requireIssuer(issuer)
                    .requireSubject(subject)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtCustomException(JwtError.JWT_ACCESS_TOKEN_TIME_EXPIRED); // 시간
        } catch (SignatureException e) {
            throw new JwtCustomException(JwtError.JWT_VALID_NOT_SIGNATURE); // 서명값 변조
        } catch (IncorrectClaimException e) {
            throw new JwtCustomException(JwtError.JWT_VALID_NOT_INCORRECT_CLAIM); // 필수 클레임
        }
    }

    private void decodeRefreshToken(String refreshToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .requireIssuer(issuer)
                    .requireSubject(subject)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtCustomException(JwtError.JWT_REFRESH_TOKEN_TIME_EXPIRED); // 시간
        } catch (SignatureException e) {
            throw new JwtCustomException(JwtError.JWT_VALID_NOT_SIGNATURE); // 서명값 변조
        } catch (IncorrectClaimException e) {
            throw new JwtCustomException(JwtError.JWT_VALID_NOT_INCORRECT_CLAIM); // 필수 클레임
        }
    }
}
