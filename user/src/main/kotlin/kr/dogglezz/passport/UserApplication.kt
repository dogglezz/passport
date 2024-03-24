package kr.dogglezz.passport

import kr.dogglezz.passport.domain.User
import kr.dogglezz.passport.domain.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@EnableDiscoveryClient
@SpringBootApplication
class UserApplication(
    private val userRepository: UserRepository
) {

    @Bean
    fun applicationRunner(): ApplicationRunner {
        return ApplicationRunner {
            userRepository.save(User(name = "hello", password =  "password"))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}