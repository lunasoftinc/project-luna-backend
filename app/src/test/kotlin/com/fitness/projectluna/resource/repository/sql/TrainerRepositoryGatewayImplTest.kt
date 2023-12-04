package com.fitness.projectluna.resource.repository.sql

import com.fitness.projectluna.exception.IncorrectPasswordException
import com.fitness.projectluna.gateway.TrainerRepositoryGateway
import com.fitness.projectluna.integration.IntegrationTest
import com.fitness.projectluna.model.Trainer
import com.fitness.projectluna.resource.repository.sql.spring.TrainerRepositorySpring
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

  @Test
  fun `delete trainer by id successfully`() = runTest {
      val trainer = Trainer(name = "Marcelo", email = "test@test.com", password = "123456")
      val createdTrainer = trainerRepositoryGateway.create(trainer)

      trainerRepositoryGateway.deleteTrainerById(createdTrainer.id!!)

      val findTrainer = trainerRepositorySpring.findById(createdTrainer.id!!)

      Assertions.assertNull(findTrainer)
  }

  @Test
  fun `find trainer by email and password successfully`() = runTest {
      val trainer = Trainer(name = "Marcelo", email = "test@test.com", password = "123456")
      trainerRepositoryGateway.create(trainer)

      val foundTrainer = trainerRepositoryGateway.findTrainerByEmail(trainer.email, trainer.password)

      Assertions.assertNotNull(foundTrainer)
      Assertions.assertEquals(trainer.email, foundTrainer?.email)
  }

  @Test
  fun `find trainer by non-existing email and password`() = runTest {
      val nonExistingEmail = "non-existing@test.com"
      val nonExistingPassword = "non-existing"

      val foundTrainer = trainerRepositoryGateway.findTrainerByEmail(nonExistingEmail, nonExistingPassword)

      Assertions.assertNull(foundTrainer)
  }

  @Test
  fun `find trainer by email and wrong password`() = runTest {
    val trainer = Trainer(name = "Marcelo", email = "test@test.com", password = "123456")
    trainerRepositoryGateway.create(trainer)
    val wrongPassword = "wrong-password"

    val exception = assertThrows<IncorrectPasswordException> {
      trainerRepositoryGateway.findTrainerByEmail(trainer.email, wrongPassword)
    }

    Assertions.assertEquals("The password is incorrect", exception.message)

  }

}