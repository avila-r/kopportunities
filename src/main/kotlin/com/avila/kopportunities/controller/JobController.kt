package com.avila.kopportunities.controller

import com.avila.kopportunities.model.Job
import com.avila.kopportunities.service.JobService

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity as http
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@RequestMapping("/api/v1/jobs")
@RestController class JobController ( private val service: JobService ) {

    data class Request(
        val title: String,
        val description: String,
        val company: String,
        val location: String,
        val type: String,
        val salary: BigDecimal,
        val requirements: String,
        val responsibilities: String
    ) {
        fun build():Job =
            Job(
                title = this.title,
                description = this.description,
                company = this.company,
                location = this.location,
                type = this.type,
                salary = this.salary,
                requirements = this.requirements,
                responsibilities = this.responsibilities
            )
    }

    data class Response(
        val id: UUID?,
        val lastModified: LocalDate?,
        val status: String?,
        val title: String,
        val description: String,
        val company: String,
        val location: String,
        val type: String,
        val salary: BigDecimal,
        val requirements: String,
        val responsibilities: String
    )

    @GetMapping("/id/{id}")
    fun getJob(@PathVariable id: String):http<Response> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getJob(UUID.fromString(id)).build())
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @PostMapping
    fun postJob(@RequestBody request: Request):http<Response> =
        try {
            http
                .status(HttpStatus.CREATED)
                .body(service.insertJob(request.build()).build())
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }
}