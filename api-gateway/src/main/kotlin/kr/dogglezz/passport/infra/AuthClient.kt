package kr.dogglezz.passport.infra

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient("AUTH-SERVICE")
interface AuthClient {
    @PostMapping("/api/v1/passport")
    fun issuePassport(@RequestHeader(name = HttpHeaders.AUTHORIZATION) token: String): String
}