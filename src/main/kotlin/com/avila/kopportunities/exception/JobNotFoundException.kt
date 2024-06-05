package com.avila.kopportunities.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity as http
import org.springframework.web.bind.annotation.ExceptionHandler

class JobNotFoundException : Exception("Job not found")

@ExceptionHandler( JobNotFoundException::class )
fun handleJotNotFoundException(e: JobNotFoundException): http<HttpErrorResponse> =
    http
        .status(HttpStatus.NOT_FOUND)
        .body(HttpErrorResponse(code = 404, status = HttpStatus.NOT_FOUND.toString(), message = e.message ?: "Job not found"))