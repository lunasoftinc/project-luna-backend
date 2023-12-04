package com.fitness.projectluna.application.routes

import com.fitness.projectluna.application.handler.TrainerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

private const val PATH = "/luna/v1/trainer"

@Configuration
class TrainerController(
  private val exceptionHandler: ExceptionHandler
) {

  @Bean
  fun trainerCreate(trainerHandler: TrainerHandler) = coRouter {
    POST("$PATH/signup", trainerHandler::create)
    onError<Exception> { exception, request -> exceptionHandler.handler(exception, request) }
  }

}