package com.fitness.projectluna.integration

import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port = 0)
abstract class IntegrationTest {

  @Autowired
  lateinit var entityTemplate: R2dbcEntityTemplate

  @BeforeEach
  fun setupDatabase() {
    val script = ClassPathResource("initialize.sql").inputStream.bufferedReader().use { it.readText() }
    entityTemplate.databaseClient.sql(script).then().block()
  }

  @AfterEach
  fun tearDown() {
    WireMock.resetAllRequests()
    entityTemplate.databaseClient.sql("DROP ALL OBJECTS").then().block()
  }
}