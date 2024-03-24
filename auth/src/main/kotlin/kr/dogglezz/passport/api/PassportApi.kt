package kr.dogglezz.passport.api

import kr.dogglezz.passport.application.PassportService
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class PassportApi(
    private val passportService: PassportService
) {

    @PostMapping("/api/v1/passport")
    fun issuePassport(@RequestHeader(name = HttpHeaders.AUTHORIZATION) bearerToken: String) {
    }

}