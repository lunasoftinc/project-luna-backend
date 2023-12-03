package com.fitness.projectluna.resource.repository.sql

import com.fitness.projectluna.gateway.TrainerRepositoryGateway
import com.fitness.projectluna.model.Trainer
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
    return trainerRepositorySpring.save(TrainerEntity(trainer, cryptography)).toDomain()
  }

  override suspend fun deleteTrainerById(trainerID: Long) {
    return trainerRepositorySpring.deleteById(trainerID)
  }

  override suspend fun findTrainerByEmail(email: String, password: String): Trainer? {
    val encryptedPassword = cryptography.encrypt(password)
    return trainerRepositorySpring.findTrainerEntityByEmailAndPassword(email, encryptedPassword).toDomain()
  }
}