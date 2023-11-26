package com.fitness.projectluna.repository

interface Transaction {

  suspend fun <T> open(block: suspend () -> T): T
}