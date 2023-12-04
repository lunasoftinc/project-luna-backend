package com.fitness.projectluna.application.config

import mu.KotlinLogging
import org.springframework.core.env.AbstractEnvironment
import java.util.*

private val logger = KotlinLogging.logger {}

fun setupEnv() {
  TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
  System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, getCurrentProfile())
}

fun getCurrentProfile(): String {
  logger.info { "TESTTTTT =>> ${System.getenv("SCOPE")}" }
  val scope = System.getenv("SCOPE") ?: "development"
  return when {
    scope.contains("stage", true) -> "stage"
    scope.contains("production", true) -> "production"
    else -> scope
  }
}