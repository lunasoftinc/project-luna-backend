package com.fitness.projectluna.exception

class IncorrectPasswordException(
  override val message: String = "Incorrect password",
  override val type: String = "INCORRECT_PASSWORD",
  override val details: Map<String, String> = emptyMap()
): BaseException(message, type, details)