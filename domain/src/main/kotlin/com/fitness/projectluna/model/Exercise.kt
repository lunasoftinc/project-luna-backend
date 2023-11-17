package com.fitness.projectluna.model

data class Exercise(
  val id: Long? = null,
  val name: String,
  val description: String?,
  val category: String,
  val banner: String?,
  val icon: String?
)