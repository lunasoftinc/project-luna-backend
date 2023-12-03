package com.fitness.projectluna.resource.repository.sql

import com.fitness.projectluna.repository.Transaction
import com.fitness.projectluna.service.Loggable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TransactionImpl: Transaction, Loggable {

  @Transactional
  override suspend fun <T> open(block: suspend () -> T) = log.info("Open transactional").let {
    block()
  }.also {
    log.info("Close transactional")
  }
}