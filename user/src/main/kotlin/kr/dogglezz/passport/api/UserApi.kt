package kr.dogglezz.passport.api

import kr.dogglezz.passport.application.UserService
import kr.dogglezz.passport.dto.LoginRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserApi(
    private val userService: UserService,
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest) = userService.login(request)

    @GetMapping("/{id}")
    fun getById(@PathVariable(name = "id") id: Long) = userService.getById(id)
}
