package com.fitness.projectluna.resource

import com.fitness.projectluna.repository.Transaction

class TransactionMock: Transaction {
  override suspend fun <T> open(block: suspend () -> T) = block()
}