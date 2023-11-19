package com.fitness.projectluna.gateway

import com.fitness.projectluna.model.Trainer

interface TrainerRepositoryGateway {

  suspend fun create(trainer: Trainer): Trainer

  suspend fun deleteTrainerById(trainerID: String)

  suspend fun findTrainerByEmail(email: String, password: String): Trainer

}