package com.avila.kopportunities.exception

data class HttpErrorResponse(
    val code: Int,
    val status: String,
    val message: String
)