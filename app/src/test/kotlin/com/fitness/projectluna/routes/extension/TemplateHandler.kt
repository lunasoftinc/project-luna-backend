package com.fitness.projectluna.routes.extension

import com.fitness.projectluna.application.routes.extensions.BadRequestHandler
import com.fitness.projectluna.exception.BadRequestException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

@ExperimentalCoroutinesApi
class TemplateHandler {

  @Test
  fun `should handler exception internal server error when an unknown error has occurred`() {

    val handler = BadRequestHandler()
    val exception = mockk<BadRequestException>()

    coEvery { exception.type } returns "INVALID_TYPE"

    runTest {
      val result = handler.handler(exception)
      Assertions.assertThat(result.second.value()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
    }
  }
}