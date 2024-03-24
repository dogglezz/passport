package kr.dogglezz.passport.api

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestAPi {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun testPassport(@RequestHeader("X-Passport") passport: String) {
        log.info("passport data = {}", passport)
    }
}