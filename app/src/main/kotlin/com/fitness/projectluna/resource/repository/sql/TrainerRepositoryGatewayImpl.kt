package com.fitness.projectluna.resource.repository.sql

import com.fitness.projectluna.exception.RepositoryException
import com.fitness.projectluna.gateway.TrainerRepositoryGateway
import com.fitness.projectluna.model.Trainer
import com.fitness.projectluna.model.TypeException
import com.fitness.projectluna.resource.repository.entity.TrainerEntity
import com.fitness.projectluna.resource.repository.sql.spring.TrainerRepositorySpring
import com.fitness.projectluna.service.Cryptography
import org.springframework.stereotype.Component

@Component
class TrainerRepositoryGatewayImpl(
  val trainerRepositorySpring: TrainerRepositorySpring,
  val cryptography: Cryptography
): TrainerRepositoryGateway {
  override suspend fun create(trainer: Trainer): Trainer {
    return trainer.id?.let {
      throw RepositoryException(
        "Trainer already exists",
        TypeException.TRAINER_ALREADY_EXISTS.name
      )
    } ?: trainerRepositorySpring.save(TrainerEntity(trainer, cryptography)).toDomain()
  }

  override suspend fun deleteTrainerById(trainerID: Long) {
    return trainerRepositorySpring.deleteById(trainerID)
  }

  override suspend fun findTrainerByEmail(email: String, password: String): Trainer {
    return trainerRepositorySpring.findTrainerEntityByEmailAndPassword(email, password)
      .takeIf {
        cryptography.checkPassword(password, it.password)
      }?.toDomain()
      ?: throw RepositoryException("Password Incorrect", TypeException.PASSWORD_INCORRECT.name)
  }
}