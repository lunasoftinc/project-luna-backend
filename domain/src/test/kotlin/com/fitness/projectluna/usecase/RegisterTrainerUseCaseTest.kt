package com.fitness.projectluna.usecase

import com.fitness.projectluna.exception.BusinessException
import com.fitness.projectluna.gateway.TrainerRepositoryGateway
import com.fitness.projectluna.model.Trainer
import com.fitness.projectluna.model.TypeException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@ExperimentalCoroutinesApi
class RegisterTrainerUseCaseTest {

  @MockK
  private lateinit var trainerRepositoryGateway: TrainerRepositoryGateway

  @InjectMockKs
  private lateinit var target: RegisterTrainerUseCase

  @BeforeEach
  fun setup() {
    MockKAnnotations.init(this)
  }

  @Test
  fun `should create trainer`() {
    //given
    val trainer = Trainer(29,"Marcelo", "test@gemail", "1233")
    coEvery { trainerRepositoryGateway.findTrainerByEmail(trainer.email, trainer.password) } returns null
    coEvery { trainerRepositoryGateway.create(trainer) } returns trainer

    //when
    runTest {
      val result = target.execute(trainer)

      //then
      assertEquals(trainer, result)
      coVerify(exactly = 1) { trainerRepositoryGateway.create(trainer) }
    }

  }

  @Test
  fun `should create trainer when trainer does not exist`() {
    //given
    val trainer = Trainer(29,"Marcelo", "test@gemail", "1233")
    coEvery { trainerRepositoryGateway.findTrainerByEmail(trainer.email, trainer.password) } returns null
    coEvery { trainerRepositoryGateway.create(trainer) } returns trainer

    //when
    runTest {
      val result = target.execute(trainer)

      //then
      assertEquals(trainer, result)
      coVerify(exactly = 1) { trainerRepositoryGateway.create(trainer) }
    }
  }

  @Test
  fun `should throw exception when trainer already exists`() {
    //given
    val trainer = Trainer(29,"Marcelo", "test@gemail", "1233")
    coEvery { trainerRepositoryGateway.findTrainerByEmail(trainer.email, trainer.password) } returns trainer

    //when
    val exception = assertThrows<BusinessException> {
      runTest {
        target.execute(trainer)
      }
    }

    //then
    assertEquals("Trainer already exists", exception.message)
    assertEquals(TypeException.TRAINER_ALREADY_EXISTS.name, exception.type)
    coVerify(exactly = 0) { trainerRepositoryGateway.create(trainer) }
  }
}