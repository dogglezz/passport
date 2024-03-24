package kr.dogglezz.passport.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    val role: Role = Role.ROLE_USER,
) {
    fun isNotSamePassword(password: String): Boolean {
        return this.password != password
    }
}