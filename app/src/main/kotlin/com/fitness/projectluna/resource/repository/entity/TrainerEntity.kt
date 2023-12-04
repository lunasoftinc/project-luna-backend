package com.fitness.projectluna.resource.repository.entity

import com.fitness.projectluna.model.Trainer
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("trainer")
data class TrainerEntity(
  @Id val id: Long? = null,
  val name: String,
  val email: String,
  val password: String
) {

  constructor(trainer: Trainer): this(
    id = trainer.id,
    name = trainer.name,
    email = trainer.email,
    password = trainer.password
  )

  fun toDomain() = Trainer(
    id = id,
    name = name,
    email = email,
    password = password
  )
}