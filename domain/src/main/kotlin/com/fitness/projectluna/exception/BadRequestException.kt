package com.fitness.projectluna.exception

class BadRequestException(
  override val message: String,
  override val type: String,
  override val details: Map<String, String> = emptyMap()
) : BaseException(message, type, details)