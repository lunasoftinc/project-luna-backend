package com.fitness.projectluna.resource.repository.sql.spring

import com.fitness.projectluna.resource.repository.entity.TrainerEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TrainerRepositorySpring: CoroutineCrudRepository<TrainerEntity, Long> {
  suspend fun findTrainerEntityByEmail(email: String): TrainerEntity?
}