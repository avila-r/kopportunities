package com.avila.kopportunities.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.http.ResponseEntity as http
import org.springframework.web.bind.annotation.ExceptionHandler

class InvalidApplicationException : Exception("Invalid application")

@ControllerAdvice class InvalidApplicationExceptionHandler {
    @ExceptionHandler( InvalidApplicationException::class )
    fun handleApplicationNotFoundException(e: InvalidApplicationException): http<HttpErrorResponse> =
        http
            .status(HttpStatus.BAD_REQUEST)
            .body(HttpErrorResponse(
                status = HttpStatus.BAD_REQUEST.toString(),
                message = e.message ?: "Invalid application"
            ))
}