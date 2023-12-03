package com.fitness.projectluna.application.routes.extensions

import com.fitness.projectluna.application.dto.HttpErrorResponse
import com.fitness.projectluna.exception.BaseException
import com.fitness.projectluna.service.Loggable
import org.springframework.http.HttpStatus

abstract class TemplateHandler: Loggable {

  abstract suspend fun applyHandler(e: BaseException): Pair<HttpErrorResponse, HttpStatus>

  abstract suspend fun isTypeException(type: String): Boolean

  suspend fun handler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
    return if (isTypeException(e.type)) {
      applyHandler(e)
    } else {
      log.error("An unknown error has occurred ${e.type}")
      HttpErrorResponse("INTERNAL_SERVER_ERROR", "Unknown error") to HttpStatus.INTERNAL_SERVER_ERROR
    }
  }

  abstract fun getTypes(): Set<String>
}