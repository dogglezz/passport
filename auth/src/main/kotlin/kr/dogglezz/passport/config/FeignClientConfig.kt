package kr.dogglezz.passport.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["kr.dogglezz.passport"])
@Configuration
class FeignClientConfig