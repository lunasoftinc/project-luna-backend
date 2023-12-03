package com.fitness.projectluna.application.handler

import com.fitness.projectluna.application.dto.request.TrainerRequest
import com.fitness.projectluna.usecase.RegisterTrainerUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait


@Component
class TrainerHandler(
  private val registerTrainerUseCase: RegisterTrainerUseCase
) {

  suspend fun create(request: ServerRequest): ServerResponse {
    val register = request.awaitBody<TrainerRequest>().toDomain()

    val trainer = registerTrainerUseCase.execute(register)

    return ServerResponse.ok().bodyValueAndAwait(trainer)
  }
}