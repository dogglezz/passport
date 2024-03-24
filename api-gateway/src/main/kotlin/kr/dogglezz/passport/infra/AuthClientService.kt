package kr.dogglezz.passport.infra

import org.springframework.stereotype.Component

@Component
class AuthClientService {

    fun issuePassport(token: String): String {
        return try {
             "eyJnZW5lcmF0ZUlkIjoiYkJEdlFJZGZ5Q1laNHFYSmZIdFZ5VG5nZGJ0aU9xSmhOUDB1TnlKTGJIcz0iLCJ1c2VyIjp7ImlkIjoxLCJuYW1lIjoiaGVsbG8ifX0="
        } catch (e: Exception) {
            throw IllegalArgumentException("passport 발급 실패")
        }
    }
}