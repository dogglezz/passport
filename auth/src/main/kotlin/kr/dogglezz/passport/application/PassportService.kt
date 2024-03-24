package kr.dogglezz.passport.application

import kr.dogglezz.passport.infra.UserClientService
import kr.dogglezz.passport.jwt.JwtProvider
import org.springframework.stereotype.Component

@Component
class PassportService(
    private val jwtProvider: JwtProvider,
    private val userClientService: UserClientService,
) {

    fun issuePassport(token: String) {
        val id = jwtProvider.getId(token)
        val userInfo = userClientService.getById(id)

        // Passport Generate
    }
}