package com.fitness.projectluna.resource.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("clients")
data class ClientEntity(
  @Id val id: Long? = null,
  val name: String,
  val email: String,
  val password: String,
  val age: Int,
  val gender: String,
  val height: Float,
  val weight: Float,
  val trainerId: Long?
)