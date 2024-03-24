package kr.dogglezz.passport.infra

import kr.dogglezz.passport.domain.UserInfo
import org.springframework.stereotype.Component

@Component
class UserClientService(
    private val userClient: UserClient
) {
    fun getById(id: Long): UserInfo {
        return try {
            userClient.getUserById(id)
        } catch (e: Exception) {
            TODO("Exception catching")
        }
    }

}