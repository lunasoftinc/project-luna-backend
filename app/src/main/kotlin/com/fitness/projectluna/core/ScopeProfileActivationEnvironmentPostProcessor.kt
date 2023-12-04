package com.fitness.projectluna.core

import com.fitness.projectluna.service.Loggable
import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.get

class ScopeProfileActivationEnvironmentPostProcessor: EnvironmentPostProcessor, Loggable {
  override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
    environment["SCOPE"]?.also {
      val profile = it.split("-").last()
      log.info("PEGAR O PROFILE ==> $profile")
      environment.addActiveProfile(profile)
    }
  }
}