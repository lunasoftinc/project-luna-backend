package com.fitness.projectluna.resource.repository.sql

import com.fitness.projectluna.gateway.TrainerRepositoryGateway
import com.fitness.projectluna.integration.IntegrationTest
import com.fitness.projectluna.model.Trainer
import com.fitness.projectluna.resource.repository.sql.spring.TrainerRepositorySpring
import com.fitness.projectluna.service.Cryptography
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@ExperimentalCoroutinesApi
class TrainerRepositoryGatewayImplTest(
  @Autowired val trainerRepositoryGateway: TrainerRepositoryGateway,
  @Autowired val trainerRepositorySpring: TrainerRepositorySpring,
): IntegrationTest() {

  @Test
  fun `create trainer with success`() = runTest {
    val response = trainerRepositoryGateway.create(Trainer(name = "Marcelo", email = "test@test.com", password = "123456"))

    val findTrainer = trainerRepositorySpring.findById(response.id!!)

    Assertions.assertEquals(findTrainer?.name, response.name)
  }

}