package com.fitness.projectluna.application.routes.extensions

import com.fitness.projectluna.application.dto.HttpErrorResponse
import com.fitness.projectluna.exception.BaseException
import com.fitness.projectluna.model.TypeException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class BadRequestHandler: TemplateHandler() {
  override suspend fun applyHandler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
    return HttpErrorResponse(e.type, e.message, e.details) to HttpStatus.BAD_REQUEST
  }

  override suspend fun isTypeException(type: String): Boolean = getTypes().contains(type)

  override fun getTypes(): Set<String> = setOf(
    TypeException.PROCESS_NOT_IMPLEMENTED.name,
    TypeException.INVALID_REQUEST.name,
    TypeException.PROCESS_VALIDATED.name
  )
}