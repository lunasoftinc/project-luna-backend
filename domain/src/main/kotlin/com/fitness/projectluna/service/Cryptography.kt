package com.fitness.projectluna.service

interface Cryptography {

  fun encrypt(value: String): String

  fun checkPassword(password: String, hash: String): Boolean
}