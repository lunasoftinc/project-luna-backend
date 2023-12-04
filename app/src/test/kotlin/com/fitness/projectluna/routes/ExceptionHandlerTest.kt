package com.fitness.projectluna.routes

import com.fitness.projectluna.application.routes.ExceptionHandler
import com.fitness.projectluna.application.routes.extensions.BadGatewayHandler
import com.fitness.projectluna.application.routes.extensions.BadRequestHandler
import com.fitness.projectluna.exception.BadRequestException
import com.fitness.projectluna.exception.ComunicationExternalException
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest

@ExperimentalCoroutinesApi
class ExceptionHandlerTest {

  val handler = ExceptionHandler(mapOf(
    setOf("PROCESS_VALIDATED") to BadRequestHandler(),
    setOf("PUBLISHER_ERROR") to BadGatewayHandler()
  ))

  val request = mockk<ServerRequest>()

  @Test
  fun `should handler exception bad request`() {
    val exception = BadRequestException("message", "PROCESS_VALIDATED")
    runTest {
      val result = handler.handler(exception, request)
      Assertions.assertThat(result.statusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value())
    }
  }

  @Test
  fun `should handler exception internal server error`() {
    val exception = BadRequestException("message", "INVALID_TYPE")
    runTest {
      val result = handler.handler(exception, request)
      Assertions.assertThat(result.statusCode().value()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
    }
  }

  @Test
  fun `should handler exception internal server error when not mapped error`() {
    val exception = IllegalArgumentException("message")
    runTest {
      val result = handler.handler(exception, request)
      Assertions.assertThat(result.statusCode().value()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
    }
  }

  @Test
  fun `should handler an exception bad gateway`() {
    val exception = ComunicationExternalException("message", "PUBLISHER_ERROR")
    runTest {
      val result = handler.handler(exception, request)
      Assertions.assertThat(result.statusCode().value()).isEqualTo(HttpStatus.BAD_GATEWAY.value())
    }
  }
}