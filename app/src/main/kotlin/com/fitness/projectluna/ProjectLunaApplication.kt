package com.fitness.projectluna

import com.fitness.projectluna.application.config.setupEnv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableR2dbcAuditing
class ProjectLunaApplication

fun main(args: Array<String>) {
  setupEnv()
  runApplication<ProjectLunaApplication>(*args)
}
