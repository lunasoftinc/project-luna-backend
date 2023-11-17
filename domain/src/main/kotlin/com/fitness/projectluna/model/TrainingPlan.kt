package com.fitness.projectluna.model

data class TrainingPlan(
  val id: Long? = null,
  val name: String,
  val goal: String,
  val personalTrainer: Trainer,
  val client: Client
)