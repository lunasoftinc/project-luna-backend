package com.fitness.projectluna.resource.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("training_plans")
data class TrainingPlanEntity(
  @Id
  val id: Long? = null,
  val name: String,
  val goal: String,
  val startDate: LocalDateTime,
  val endDate: LocalDateTime,
  val personalTrainerId: Long?,
  val clientId: Long?
)