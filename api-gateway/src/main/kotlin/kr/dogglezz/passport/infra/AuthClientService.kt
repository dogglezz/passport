package kr.dogglezz.passport.infra

import org.springframework.stereotype.Component

@Component
class AuthClientService(
    private val authClient: AuthClient,
) {

    fun issuePassport(token: String): String {
        return try {
            authClient.issuePassport(token)
        } catch (e: Exception) {
            throw IllegalArgumentException("passport 발급 실패")
        }
    }
}