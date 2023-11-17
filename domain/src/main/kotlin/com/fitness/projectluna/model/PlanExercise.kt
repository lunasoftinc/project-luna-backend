package com.fitness.projectluna.model

data class PlanExercise(
  val id: Long? = null,
  val trainingPlan: TrainingPlan,
  val exercise: Exercise
)