package kr.dogglezz.passport.support

import com.fasterxml.jackson.databind.ObjectMapper
import kr.dogglezz.passport.domain.Passport
import kr.dogglezz.passport.domain.UserInfo
import org.springframework.stereotype.Component
import java.util.Base64


@Component
class PassportGenerator(
    private val keyEncoder: KeyEncoder,
    private val objectMapper: ObjectMapper,
) {

    fun generate(user: UserInfo): String {
        val userInfoString = objectMapper.writeValueAsString(user)
        val key = keyEncoder.createKey(userInfoString)
        val passport = Passport(key, user)
        val passportString = objectMapper.writeValueAsString(passport)
        return Base64.getEncoder()
            .encodeToString(passportString.toByteArray())
    }
}