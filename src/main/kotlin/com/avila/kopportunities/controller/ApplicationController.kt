package com.avila.kopportunities.controller

import com.avila.kopportunities.model.Application
import com.avila.kopportunities.model.ApplicationRequestDTO
import com.avila.kopportunities.model.ApplicationResponseDTO
import com.avila.kopportunities.service.ApplicationService

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.http.ResponseEntity as http
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.time.LocalDate
import java.util.UUID

@RequestMapping("/api/v1/applications")
@RestController class ApplicationController ( private val service: ApplicationService ) {

    data class Response (
        val code: Int,
        val status: String,
        val message: String
    )

    @PostMapping("/job/{jobId}")
    fun postApplication(
        @PathVariable jobId: String?,
        @RequestBody request: ApplicationRequestDTO?
    ) = http.status(HttpStatus.CREATED)
            .body(service.insert(request, UUID.fromString(jobId)).build())

    @PatchMapping
    fun updateApplication(@RequestBody application: Application):http<ApplicationResponseDTO> =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.update(application).build())
        } catch (e: Exception) {
            println(e)
            http.badRequest().build()
        }

    @DeleteMapping("/id/{id}")
    fun deleteApplication(@PathVariable id: String):http<Response> =
        try {
            http
                .status(HttpStatus.OK)
                .body(
                    if (service.deleteById(UUID.fromString(id))) {
                        Response(200, "OK", "Application deleted successfully")
                    } else {
                        Response(400, "Bad Request", "Application not found")
                    }
                )
        } catch (e: Exception) {
            println(e)
            http.badRequest().build()
        }

    @GetMapping
    fun getAllApplications() =
        http
            .status(HttpStatus.OK)
            .body(service.listAll().map { it.build() })

    @GetMapping("/id/{id}")
    fun getApplication(@PathVariable id: String) =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getById(UUID.fromString(id)).build())
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/job/{jobId}")
    fun getApplicationsByJobId(@PathVariable jobId: String) =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByJobId(UUID.fromString(jobId)).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/applicant/{applicantName}")
    fun getApplicationsByApplicantName(@PathVariable applicantName: String) =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByApplicantName(applicantName).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/email/{applicantEmail}")
    fun getApplicationsByApplicantEmail(@PathVariable applicantEmail: String) =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByApplicantEmail(applicantEmail).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/resume/{resumeUrl}")
    fun getApplicationsByResumeUrl(@PathVariable resumeUrl: String) =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByResumeUrl(resumeUrl).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/cover/{coverLetter}")
    fun getApplicationsByCoverLetter(@PathVariable coverLetter: String) =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByCoverLetter(coverLetter).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/date/{appliedDate}")
    fun getApplicationsByAppliedDate(@PathVariable appliedDate: String) =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByAppliedDate(LocalDate.parse(appliedDate)).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    @GetMapping("/status/{status}")
    fun getApplicationsByStatus(@PathVariable status: String) =
        try {
            http
                .status(HttpStatus.OK)
                .body(service.getByStatus(status).map { it.build() })
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

}