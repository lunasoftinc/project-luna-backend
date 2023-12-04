package com.fitness.projectluna.application.dto.request

import com.fitness.projectluna.model.Trainer

data class TrainerRequest(
  val name: String,
  val email: String,
  val password: String
) {

  fun toDomain() = Trainer(
    name = name,
    email = email,
    password = password
  )
}