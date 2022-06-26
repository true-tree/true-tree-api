package com.truetree.common.jwt;

import com.truetree.exception.jwt.JwtCustomException;
import com.truetree.exception.jwt.JwtError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("JWT Token 생성 및 검증 테스트")
public class JwtTokenUtilTest {

    Integer accessTokenExpirationTime = 2000;
    Integer refreshTokenExpirationTime = 2000;
    String secretKey = "시크릿키는256비트보커야합니다그러므로256비트를넘겨야합니다";
    String claimKey = "claim-key-test";
    String issuer = "issuer";
    String subject = "subject";

    Long memberId = 1L;

    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(accessTokenExpirationTime, refreshTokenExpirationTime, secretKey, claimKey, issuer, subject) ;

    @Nested
    @DisplayName("AccessToken Test")
    public class AccessTokenTest {
        @Test
        @DisplayName("AccessToken 토큰 생성 테스트")
        public void createTokenTest() {
            assertThat(jwtTokenUtil.createAccessToken(memberId)).isNotNull();
        }

        @Test
        @DisplayName("토큰 검증 테스트 (토큰을 디코딩하고 페이로드를 검증)")
        public void verifyAccessTokenTest() {

            // 준비
            String accessToken = jwtTokenUtil.createAccessToken(memberId);

            // 실행
            Long decodeTokenValue = jwtTokenUtil.verifyAccessToken(accessToken);

            // 검증
            assertThat(decodeTokenValue).isEqualTo(memberId);
        }


        @Test
        @DisplayName("JWT AccessToken 만료 시간 경과시 BizException 발생")
        public void accessTokenExpirationErrorTest() throws InterruptedException {

            // 준비
            String accessToken = jwtTokenUtil.createAccessToken(memberId);

            // 시간 초과를 위한 슬립
            Thread.sleep(accessTokenExpirationTime + 1000);

            // 검증
            assertThatThrownBy(() -> jwtTokenUtil.verifyAccessToken(accessToken))
                    .isInstanceOf(JwtCustomException.class)
                    .hasMessage(JwtError.JWT_ACCESS_TOKEN_TIME_EXPIRED.getMessage());
        }

        @Test
        @DisplayName("JWT AccessToken 서명키 위조시 BizException 발생")
        public void accessTokenSignatureException() {

            // 준비
            // 위조 된 서명키를 가진 객체를 생성
            String forgerySecretKey = "위조된서명을가지고있는비밀키를생성해버립니다.";
            JwtTokenUtil forgeryProvider = new JwtTokenUtil(accessTokenExpirationTime, refreshTokenExpirationTime, forgerySecretKey, claimKey, issuer, subject) ;


            // 실행
            // 위조된 시크릿 키 값으로 accessToken 생성
            String forgeryAccessToken = forgeryProvider.createAccessToken(memberId);

            // 검증
            assertThatThrownBy(() -> jwtTokenUtil.verifyAccessToken(forgeryAccessToken))
                    .isInstanceOf(JwtCustomException.class)
                    .hasMessage(JwtError.JWT_VALID_NOT_SIGNATURE.getMessage());
        }

    }

    @Nested
    public class RefreshTokenTest {

        @Test
        @DisplayName("RefreshToken 생성 테스트")
        public void createRefreshTokenTest() {
            assertThat(jwtTokenUtil.createRefreshToken()).isNotNull();
        }

        @Test
        @DisplayName("RefreshToken 검증 테스트")
        public void verifyRefreshTokenTest() {

            // 준비
            String refreshToken = jwtTokenUtil.createRefreshToken();

            jwtTokenUtil.verifyRefreshToken(refreshToken);
        }

        @Test
        @DisplayName("JWT AccessToken 만료 시간 경과시 BizException 발생")
        public void refreshTokenExpirationErrorTest() throws InterruptedException {

            // 준비
            String refreshToken = jwtTokenUtil.createRefreshToken();

            // 실행 시간초과를 위해 슬립
            Thread.sleep(refreshTokenExpirationTime + 1000);

            assertThatThrownBy(() -> jwtTokenUtil.verifyRefreshToken(refreshToken))
                    .isInstanceOf(JwtCustomException.class)
                    .hasMessage(JwtError.JWT_REFRESH_TOKEN_TIME_EXPIRED.getMessage());
        }

        @Test
        @DisplayName("JWT refreshToken 서명키 위조시 BizException 발생")
        public void refreshTokenSignatureException() {

            // 준비
            // 위조 된 서명키를 가진 객체를 생성
            String forgerySecretKey = "위조된서명을가지고있는비밀키를생성해버립니다.";
            JwtTokenUtil forgeryProvider = new JwtTokenUtil(accessTokenExpirationTime, refreshTokenExpirationTime, forgerySecretKey, claimKey, issuer, subject) ;


            // 실행
            // 위조된 시크릿 키 값으로 accessToken 생성
            String forgeryRefreshToken = forgeryProvider.createRefreshToken();

            assertThatThrownBy(() -> jwtTokenUtil.verifyRefreshToken(forgeryRefreshToken))
                    .isInstanceOf(JwtCustomException.class)
                    .hasMessage(JwtError.JWT_VALID_NOT_SIGNATURE.getMessage());
        }
    }


}