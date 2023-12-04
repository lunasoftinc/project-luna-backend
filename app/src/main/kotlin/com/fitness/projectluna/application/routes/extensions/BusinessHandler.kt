package com.fitness.projectluna.application.routes.extensions

import com.fitness.projectluna.application.dto.HttpErrorResponse
import com.fitness.projectluna.exception.BaseException
import com.fitness.projectluna.model.TypeException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class BusinessHandler: TemplateHandler() {
  override suspend fun applyHandler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
    return HttpErrorResponse(e.type, e.message, e.details) to HttpStatus.CONFLICT
  }

  override suspend fun isTypeException(type: String): Boolean {
    return getTypes().contains(type)
  }

  override fun getTypes(): Set<String> = setOf(
    TypeException.TRAINER_ALREADY_EXISTS.name,
  )
}