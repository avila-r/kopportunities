package com.avila.kopportunities.service

import com.avila.kopportunities.repository.ApplicationRepository
import com.avila.kopportunities.exception.ApplicationNotFoundException
import com.avila.kopportunities.exception.InvalidApplicationException
import com.avila.kopportunities.exception.JobNotFoundException
import com.avila.kopportunities.model.Application
import com.avila.kopportunities.model.ApplicationRequestDTO
import com.avila.kopportunities.repository.JobRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.time.LocalDate
import java.util.UUID

@Service class ApplicationService ( private val repository: ApplicationRepository ) {
    @Autowired private var jobRepository: JobRepository? = null

    @Transactional fun insert(request: ApplicationRequestDTO?, jobId: UUID?): Application {
        request?.let {
            val application = Application(
                job = (this.jobRepository?.findJobById(jobId) ?: throw JobNotFoundException()),
                applicantName = request.applicantName,
                applicantEmail = request.applicantEmail,
                resumeUrl = request.resumeUrl,
                coverLetter = request.coverLetter
            )

            return repository.save(application)

        } ?: throw InvalidApplicationException()
    }

    @Transactional fun update(application: Application?): Application =
        repository.save(repository.findApplicationById(application?.id) ?: throw ApplicationNotFoundException())

    @Transactional fun deleteById(id: UUID): Boolean =
        try {
            repository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }

    fun getById(id: UUID): Application =
        repository.findApplicationById(id) ?: throw ApplicationNotFoundException()

    fun getByJobId(job: UUID): List<Application> =
        repository.findAllByJobId(job) ?: throw ApplicationNotFoundException()

    fun getByApplicantName(applicantName: String): List<Application> =
        repository.findAllByApplicantName(applicantName) ?: throw ApplicationNotFoundException()

    fun getByApplicantEmail(applicantEmail: String): List<Application> =
        repository.findAllByApplicantEmail(applicantEmail) ?: throw ApplicationNotFoundException()

    fun getByResumeUrl(resumeUrl: String): List<Application> =
        repository.findAllByResumeUrl(resumeUrl) ?: throw ApplicationNotFoundException()

    fun getByCoverLetter(coverLetter: String): List<Application> =
        repository.findAllByCoverLetter(coverLetter) ?: throw ApplicationNotFoundException()

    fun getByAppliedDate(appliedDate: LocalDate): List<Application> =
        repository.findAllByAppliedDate(appliedDate) ?: throw ApplicationNotFoundException()

    fun getByStatus(status: String): List<Application> =
        repository.findAllByStatus(status) ?: throw ApplicationNotFoundException()

    fun listAll(): List<Application> =
        repository.findAll()

}