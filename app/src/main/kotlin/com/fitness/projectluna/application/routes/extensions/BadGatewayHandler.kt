package com.fitness.projectluna.application.routes.extensions

import com.fitness.projectluna.application.dto.HttpErrorResponse
import com.fitness.projectluna.exception.BaseException
import com.fitness.projectluna.model.TypeException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class BadGatewayHandler: TemplateHandler() {
  override suspend fun applyHandler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
    return HttpErrorResponse(e.type, e.message, e.details) to HttpStatus.BAD_GATEWAY
  }

  override suspend fun isTypeException(type: String): Boolean = getTypes().contains(type)

  override fun getTypes(): Set<String> = setOf(
    TypeException.PUBLISHER_ERROR.name,
    TypeException.SERVICE_UNAVAILABLE_TYPE.name
  )
}