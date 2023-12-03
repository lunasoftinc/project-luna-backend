package com.fitness.projectluna.resource

import com.fitness.projectluna.resource.repository.sql.TransactionImpl
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class TransactionImplTest {

  @Test
  fun `should process transaction with success`() = runTest {
    val transactionImpl = TransactionImpl()

    val result = transactionImpl.open { "project" + "luna" }
    Assertions.assertThat(result).isEqualTo("projectluna")
  }
}