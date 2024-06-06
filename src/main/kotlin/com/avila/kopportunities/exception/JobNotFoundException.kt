package com.avila.kopportunities.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.http.ResponseEntity as http
import org.springframework.web.bind.annotation.ExceptionHandler

class JobNotFoundException : Exception("Job not found")

@ControllerAdvice class JobNotFoundExceptionHandler {
    @ExceptionHandler( JobNotFoundException::class )
    fun handleJobNotFoundException(e: JobNotFoundException): http<HttpErrorResponse> =
        http
            .status(HttpStatus.NOT_FOUND)
            .body(HttpErrorResponse(status = HttpStatus.NOT_FOUND.toString(), message = e.message ?: "Job not found"))
}