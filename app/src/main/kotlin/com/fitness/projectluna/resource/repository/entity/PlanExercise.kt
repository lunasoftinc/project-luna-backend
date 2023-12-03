package com.fitness.projectluna.resource.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("plan_exercises")
data class PlanExercise(
  @Id
  val id: Long? = null,
  val trainingPlanId: Long?,
  val exerciseId: Long?
)