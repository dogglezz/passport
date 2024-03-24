package kr.dogglezz.passport.config

import kr.dogglezz.passport.jwt.JwtProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig(
    @Value("\${jwt.secret.key}")
    private var secretKey: String,

    @Value("\${jwt.secret.token.expired}")
    private var expiredTime: Long,
) {

    @Bean
    fun jwtTokenProvider() = JwtProvider(secretKey, expiredTime)

}