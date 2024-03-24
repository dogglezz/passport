package kr.dogglezz.passport.support

import com.fasterxml.jackson.databind.ObjectMapper
import kr.dogglezz.passport.domain.Passport
import org.springframework.stereotype.Component
import java.util.Base64

@Component
class PassportExtractor(
    private val objectMapper: ObjectMapper,
) {

    fun extract(passportHeader: String): Passport {
        val passportStr: String = String(
            Base64.getDecoder().decode(passportHeader)
        )
        // passportKey validate
        return try {
            objectMapper.readValue(passportStr, Passport::class.java)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}