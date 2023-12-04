package com.fitness.projectluna.routes

import com.fitness.projectluna.integration.IntegrationTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class PingControllerTest: IntegrationTest() {

  @Test
  fun `should return pong`() {
    webTestClient.get()
      .uri("/ping")
      .exchange()
      .expectStatus().isOk
  }
}