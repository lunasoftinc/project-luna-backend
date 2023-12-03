package com.fitness.projectluna.model

data class Client(
  val id: Long? = null,
  val name: String,
  val email: String,
  val password: String,
  val age: Int,
  val gender: String,
  val height: Float,
  val weight: Float,
  val trainer: Trainer
)