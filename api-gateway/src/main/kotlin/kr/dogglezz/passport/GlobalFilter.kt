package kr.dogglezz.passport

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response
            log.info("Global Filter baseMessage: -> {}", config.baseMessage)

            if (config.preLogger) log.info("Global Filter Start: request id -> {}", request.id)
            chain.filter(exchange)
                .then<Void>(Mono.fromRunnable<Void> {
                    if (config.postLogger) log.info(
                        "Global Filter End: response status code -> {}",
                        response.statusCode
                    )
                })
        }
    }

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean,
    )

}