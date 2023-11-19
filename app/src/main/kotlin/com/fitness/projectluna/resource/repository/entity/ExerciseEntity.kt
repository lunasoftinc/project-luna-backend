package com.fitness.projectluna.resource.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("exercises")
data class ExerciseEntity(
  @Id val id: Long? = null,
  val name: String,
  val description: String?,
  val category: String,
  val banner: String?,
  val icon: String?
)