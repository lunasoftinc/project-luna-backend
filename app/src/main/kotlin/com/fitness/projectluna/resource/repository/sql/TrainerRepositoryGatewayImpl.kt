package com.fitness.projectluna.resource.repository.sql

import com.fitness.projectluna.exception.IncorrectPasswordException
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
    val trainerWithEncryptedPassword = trainer.copy(password = cryptography.encrypt(trainer.password))
    return trainerRepositorySpring.save(TrainerEntity(trainerWithEncryptedPassword)).toDomain()
  }

  override suspend fun deleteTrainerById(trainerID: Long) {
    return trainerRepositorySpring.deleteById(trainerID)
  }

  override suspend fun findTrainerByEmail(email: String, password: String): Trainer? {
    return trainerRepositorySpring.findTrainerEntityByEmail(email)?.let { trainerEntity ->
      if (cryptography.checkPassword(password, trainerEntity.password)) {
        return trainerEntity.toDomain()
      } else {
        throw IncorrectPasswordException(
          "The password is incorrect",
          TypeException.INVALID_PASSWORD.name
        )
      }
    }
  }
}