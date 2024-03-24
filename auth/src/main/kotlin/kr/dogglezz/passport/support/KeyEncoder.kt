package kr.dogglezz.passport.support

import org.springframework.stereotype.Component
import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Component
class KeyEncoder {
    fun createKey(userInfoStringData: String): String {
        val secretKeySpec = SecretKeySpec(
            PASSPORT_SECRET_KEY.toByteArray(),
            ALGORITHM
        )
        try {
            val mac = Mac.getInstance(ALGORITHM)
            mac.init(secretKeySpec)
            return Base64.getEncoder()
                .encodeToString(mac.doFinal(userInfoStringData.toByteArray()))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    companion object {
        private const val ALGORITHM: String= "HmacSHA256"
        private const val PASSPORT_SECRET_KEY: String= "microsoftupupupupup"
    }
}