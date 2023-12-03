package com.fitness.projectluna.model

import java.time.LocalDateTime

data class TrainingPlan(
  val id: Long? = null,
  val name: String,
  val goal: String,
  val startDate: LocalDateTime,
  val endDate: LocalDateTime,
  val personalTrainer: Trainer,
  val client: Client
)