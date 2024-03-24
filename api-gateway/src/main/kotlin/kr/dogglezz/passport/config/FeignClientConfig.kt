package kr.dogglezz.passport.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["kr.dogglezz.passport"])
class FeignClientConfig