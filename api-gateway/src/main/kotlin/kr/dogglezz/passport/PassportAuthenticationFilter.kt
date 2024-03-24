package kr.dogglezz.passport

import kr.dogglezz.passport.infra.AuthClientService
import kr.dogglezz.passport.jwt.JwtProvider
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class PassportAuthenticationFilter(
    private val authClientService: AuthClientService,
    private val jwtProvider: JwtProvider
) : AbstractGatewayFilterFactory<PassportAuthenticationFilter.Config>(Config::class.java) {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val headers = request.headers

            println(headers)
            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return@GatewayFilter onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED)
            }

            val authorizationHeader = headers[HttpHeaders.AUTHORIZATION]?.first()
            val token = authorizationHeader?.replace("Bearer ", "")
            if (token.isNullOrEmpty() || jwtProvider.verityToken(token).not()) {
                print(token )
                return@GatewayFilter onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED)
            }

            val passport = authClientService.issuePassport(token)

            val newRequest = request.mutate()
                .header("X-Passport", passport)
                .build()

            chain.filter(exchange.mutate()
                .request(newRequest)
                .build())
        }
    }

    private fun onError(exchange: ServerWebExchange, errorMsg: String, httpStatus: HttpStatus): Mono<Void> {
        log.error(errorMsg)
        val response: ServerHttpResponse = exchange.response
        response.setStatusCode(httpStatus)
        return response.setComplete()
    }

    class Config
}