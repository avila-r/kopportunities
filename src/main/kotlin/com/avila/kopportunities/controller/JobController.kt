package com.avila.kopportunities.controller

import com.avila.kopportunities.model.Job
import com.avila.kopportunities.model.JobRequestDTO
import com.avila.kopportunities.model.JobResponseDTO
import com.avila.kopportunities.service.JobService

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.http.ResponseEntity as http
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.math.BigDecimal
import java.util.UUID

@RequestMapping("/api/v1/jobs")
@RestController class JobController ( private val service: JobService ) {

    data class Response(
        val code: Int,
        val status: String,
        val message: String
    )

    @PostMapping
    fun postJob(@RequestBody request: JobRequestDTO):http<JobResponseDTO> =
        try {
            http
                .status(HttpStatus.CREATED)
                .body(service.insert(request.build()).build())
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @PatchMapping
    fun updateJob(@RequestBody request: Job):http<JobResponseDTO> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.update(request).build())
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @DeleteMapping("/id/{id}")
    fun deleteJob(@PathVariable id: String):http<Response> =
        try {
            http
                .status(HttpStatus.OK)
                .body(
                    if (service.deleteById(UUID.fromString(id))) {
                        Response(200, "OK", "Job deleted successfully")
                    } else {
                        Response(400, "Bad Request", "Job not found")
                    }
                )
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/id/{id}")
    fun getJob(@PathVariable id: String):http<JobResponseDTO> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getById(UUID.fromString(id)).build())
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping
    fun getAllJobs():http<List<JobResponseDTO>> =
        http
            .status(HttpStatus.OK)
            .body(service.listAll().map { it.build() })

    @GetMapping("/title/{title}")
    fun getJobByTitle(@PathVariable title: String):http<List<JobResponseDTO>> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByTitle(title).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/company/{company}")
    fun getJobByCompany(@PathVariable company: String):http<List<JobResponseDTO>> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByCompany(company).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/location/{location}")
    fun getJobByLocation(@PathVariable location: String):http<List<JobResponseDTO>> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByLocation(location).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/type/{type}")
    fun getJobByType(@PathVariable type: String):http<List<JobResponseDTO>> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByType(type).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/salary/{salary}")
    fun getJobBySalary(@PathVariable salary: BigDecimal):http<List<JobResponseDTO>> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getBySalary(salary).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/requirements/{requirements}")
    fun getJobByRequirements(@PathVariable requirements: String):http<List<JobResponseDTO>> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByRequirements(requirements).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/responsibilities/{responsibilities}")
    fun getJobByResponsibilities(@PathVariable responsibilities: String):http<List<JobResponseDTO>> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByResponsibilities(responsibilities).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

}