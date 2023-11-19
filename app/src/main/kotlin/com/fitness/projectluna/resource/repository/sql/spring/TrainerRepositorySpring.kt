package com.fitness.projectluna.resource.repository.sql.spring

import com.fitness.projectluna.resource.repository.entity.TrainerEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TrainerRepositorySpring: CoroutineCrudRepository<TrainerEntity, String> {
  suspend fun findTrainerEntityByEmailAndPassword(email: String, password: String): TrainerEntity
}