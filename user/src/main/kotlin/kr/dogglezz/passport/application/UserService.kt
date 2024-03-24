package kr.dogglezz.passport.application

import kr.dogglezz.passport.domain.UserRepository
import kr.dogglezz.passport.dto.LoginRequest
import kr.dogglezz.passport.dto.UserResponse
import kr.dogglezz.passport.jwt.JwtProvider
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider
) {

    fun login(request: LoginRequest): String {
        val user = userRepository.findByName(request.name) ?: throw IllegalArgumentException("not foundUser")

        if (user.isNotSamePassword(request.password)) {
            throw IllegalArgumentException("invalid user")
        }

        return jwtProvider.createToken(user.id, user.role.toString())
    }

    fun getById(id: Long) =
        userRepository.findByIdOrNull(id)
            ?.let { UserResponse(it.id, it.name) }
            ?: throw IllegalArgumentException("not foundUser")
}