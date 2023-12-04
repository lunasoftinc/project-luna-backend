package com.fitness.projectluna.resource.service

import com.fitness.projectluna.service.Cryptography
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class CryptographyImpl: Cryptography {
  override fun encrypt(value: String): String {
    return BCrypt.hashpw(value, BCrypt.gensalt(10))
  }

  override fun checkPassword(password: String, hash: String): Boolean {
    return BCrypt.checkpw(password, hash)
  }
}