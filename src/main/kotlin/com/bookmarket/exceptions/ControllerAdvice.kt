package com.bookmarket.exceptions

import com.bookmarket.controller.response.ErrorResponse
import com.bookmarket.controller.response.FieldErrorResponse
import com.bookmarket.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest):  ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest):  ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, requesst: WebRequest) : ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            Errors.BM001.message,
            Errors.BM001.code,
            ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "invalid", it.field) }
        )
        return ResponseEntity(erro, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(ex: AccessDeniedException, request: WebRequest):  ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
            HttpStatus.FORBIDDEN.value(),
            Errors.BM000.message,
            Errors.BM000.code,
            null

        )
        return ResponseEntity(erro, HttpStatus.FORBIDDEN)
    }

}