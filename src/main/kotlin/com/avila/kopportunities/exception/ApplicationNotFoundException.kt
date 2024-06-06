package com.avila.kopportunities.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.http.ResponseEntity as http
import org.springframework.web.bind.annotation.ExceptionHandler

class ApplicationNotFoundException : Exception("Application not found")

@ControllerAdvice class ApplicationNotFoundExceptionHandler {
    @ExceptionHandler( ApplicationNotFoundException::class )
    fun handleApplicationNotFoundException(e: ApplicationNotFoundException): http<HttpErrorResponse> =
        http
            .status(HttpStatus.NOT_FOUND)
            .body(HttpErrorResponse(
                status = HttpStatus.NOT_FOUND.toString(),
                message = e.message ?: "Application not found"
            ))
}