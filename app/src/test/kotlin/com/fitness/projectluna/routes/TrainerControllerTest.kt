package com.fitness.projectluna.routes

import com.fitness.projectluna.integration.IntegrationTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

@ExperimentalCoroutinesApi
class TrainerControllerTest: IntegrationTest() {

  @Test
  fun `should create trainer`() {
    webTestClient.post()
      .uri("/luna/v1/trainer/signup")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(
        """
          {
            "name": "Marcelo",
            "email": "teste@test.com",
            "password": "123456"
          }
        """.trimIndent()
      )
      .exchange()
      .expectStatus().isOk
      .expectBody()
  }

}