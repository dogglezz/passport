package kr.dogglezz.passport.infra

import kr.dogglezz.passport.domain.UserInfo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("USER-SERVICE")
interface UserClient {

    @GetMapping("/api/v1/users/{id}")
    fun getUserById(@PathVariable(name = "id") id: Long) : UserInfo
}