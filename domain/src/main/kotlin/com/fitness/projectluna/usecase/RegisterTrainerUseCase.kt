package com.fitness.projectluna.usecase

import com.fitness.projectluna.exception.BusinessException
import com.fitness.projectluna.gateway.TrainerRepositoryGateway
import com.fitness.projectluna.model.Trainer
import com.fitness.projectluna.model.TypeException
import org.springframework.stereotype.Component

private const val TRAINER_ALREADY_EXISTS = "Trainer already exists"

@Component
class RegisterTrainerUseCase(
  private val trainerRepositoryGateway: TrainerRepositoryGateway
) {

  suspend fun execute(trainer: Trainer): Trainer {
    return trainerRepositoryGateway.findTrainerByEmail(trainer.email, trainer.password)?.let {
      throw BusinessException(TRAINER_ALREADY_EXISTS, TypeException.TRAINER_ALREADY_EXISTS.name)
    } ?: trainerRepositoryGateway.create(trainer)
  }
}