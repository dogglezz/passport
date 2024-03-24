package kr.dogglezz.passport.application

import kr.dogglezz.passport.domain.Passport
import kr.dogglezz.passport.infra.UserClientService
import kr.dogglezz.passport.jwt.JwtProvider
import kr.dogglezz.passport.support.PassportExtractor
import kr.dogglezz.passport.support.PassportGenerator
import org.springframework.stereotype.Component

@Component
class PassportService(
    private val jwtProvider: JwtProvider,
    private val userClientService: UserClientService,
    private val passportGenerator: PassportGenerator,
    private val passportExtractor: PassportExtractor
) {

    fun issuePassport(token: String): String {
        val id = jwtProvider.getId(token)
        val userInfo = userClientService.getById(id)
        return passportGenerator.generate(userInfo)
    }

    fun getPassportData(passportHeader: String): Passport {
        return  passportExtractor.extract(passportHeader)
    }
}