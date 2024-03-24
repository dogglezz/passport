package kr.dogglezz.passport.support

import kr.dogglezz.passport.domain.UserInfo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PassportGeneratorTest {

    @Autowired
    private lateinit var  passportGenerator: PassportGenerator

    @Autowired
    private  lateinit var  passportExtractor: PassportExtractor
    @Test
    fun test() {
        val passport = passportGenerator.generate(UserInfo(1L, "hello"))
        val extract = passportExtractor.extract(passport)
    }
}

