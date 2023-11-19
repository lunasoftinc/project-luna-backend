package com.fitness.projectluna.resource.repository.entity

import com.fitness.projectluna.model.Trainer
import com.fitness.projectluna.service.Cryptography
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("trainer")
data class TrainerEntity(
  @Id val id: Long? = null,
  val name: String,
  val email: String,
  val password: String
) {

  constructor(trainer: Trainer, cryptography: Cryptography): this(
    id = trainer.id,
    name = trainer.name,
    email = trainer.email,
    password = cryptography.encrypt(trainer.password)
  )

  fun toDomain() = Trainer(
    id = id,
    name = name,
    email = email,
    password = password
  )
}