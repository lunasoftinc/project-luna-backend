package com.fitness.projectluna.application.routes

import com.fitness.projectluna.application.handler.PingHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class PingController {

  @Bean
  fun pingResponse(pingHandler: PingHandler) = coRouter {
    GET("/ping", pingHandler::getPong)
  }
}