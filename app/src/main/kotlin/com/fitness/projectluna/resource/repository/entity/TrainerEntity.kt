package com.fitness.projectluna.resource.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("trainer")
data class TrainerEntity(
  @Id val id: Long? = null,
  val name: String,
  val email: String,
  val password: String
)