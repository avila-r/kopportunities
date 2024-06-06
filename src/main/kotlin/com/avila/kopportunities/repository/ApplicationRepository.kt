package com.avila.kopportunities.repository

import com.avila.kopportunities.model.Application

import org.springframework.data.repository.ListCrudRepository

import java.time.LocalDate
import java.util.UUID

interface ApplicationRepository : ListCrudRepository<Application, UUID> {
    fun findApplicationById(id: UUID?): Application?
    fun findAllByJobId(job: UUID?): List<Application>?
    fun findAllByApplicantName(applicantName: String): List<Application>?
    fun findAllByApplicantEmail(applicantEmail: String?): List<Application>?
    fun findAllByResumeUrl(resumeUrl: String?): List<Application>?
    fun findAllByCoverLetter(coverLetter: String?): List<Application>?
    fun findAllByAppliedDate(appliedDate: LocalDate?): List<Application>?
    fun findAllByStatus(status: String?): List<Application>?
}