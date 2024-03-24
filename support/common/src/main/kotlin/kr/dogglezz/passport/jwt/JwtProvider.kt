package kr.dogglezz.passport.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.Date

class JwtProvider(
    private val secretKey: String,
    private val expiredTime: Long
) {

    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun createToken(id: Long, role: String): String {
        val now = Date()
        val expiration = Date(now.time + expiredTime)
        return Jwts.builder()
            .setSubject(id.toString())
            .claim(ROLE_KEY, role)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getId(token: String?): Long =
        extractClaim(token) { claims -> claims.subject.toLong() }

    fun getRole(token: String?) = extractClaim(token) { claims ->
        claims[ROLE_KEY].toString()
    }

    private fun <T> extractClaim(token: String?, claimsResolver: (claims: Claims) -> T): T {
        val claims: Claims = getClaims(token)
        return claimsResolver(claims)
    }

    fun verityToken(token: String?): Boolean =
        try {
            !getClaims(token).expiration.before(Date())
        } catch (e: Exception) {
            false
        }

    private fun getClaims(token: String?): Claims =
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            throw IllegalArgumentException("invalid token")
        }

    companion object {
        private const val ROLE_KEY = "role"
    }

}