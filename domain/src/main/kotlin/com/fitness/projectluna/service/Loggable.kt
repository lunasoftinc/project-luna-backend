package com.fitness.projectluna.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface Loggable {

  val log: Logger
    get() = LoggerFactory.getLogger(this.javaClass)
}