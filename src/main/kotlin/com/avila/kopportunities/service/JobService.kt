package com.avila.kopportunities.service

import com.avila.kopportunities.exception.JobNotFoundException
import com.avila.kopportunities.model.Job
import com.avila.kopportunities.repository.JobRepository

import jakarta.transaction.Transactional

import org.springframework.stereotype.Service

import java.math.BigDecimal
import java.util.UUID

@Service class JobService( private val repository: JobRepository ) {

    @Transactional fun insert(job: Job?): Job =
        repository.save(job ?: throw JobNotFoundException())

    @Transactional fun update(job: Job?): Job =
        repository.save(repository.findJobById(job?.id) ?: throw JobNotFoundException())

    fun getById(id: UUID): Job =
        repository.findJobById(id) ?: throw JobNotFoundException()

    fun getByTitle(title: String): List<Job> =
        repository.findAllByTitle(title) ?: throw JobNotFoundException()

    fun getByCompany(company: String): List<Job> =
        repository.findAllByCompany(company) ?: throw JobNotFoundException()

    fun getByLocation(location: String): List<Job> =
        repository.findAllByLocation(location) ?: throw JobNotFoundException()

    fun getByType(type: String): List<Job> =
        repository.findAllByType(type) ?: throw JobNotFoundException()

    fun getBySalary(salary: BigDecimal): List<Job> =
        repository.findAllBySalary(salary) ?: throw JobNotFoundException()

    fun getByRequirements(requirements: String): List<Job> =
        repository.findAllByRequirements(requirements) ?: throw JobNotFoundException()

    fun getByResponsibilities(responsibilities: String): List<Job> =
        repository.findAllByResponsibilities(responsibilities) ?: throw JobNotFoundException()

    fun listAll(): List<Job> =
        repository.findAll()

    fun deleteById(id: UUID): Boolean =
        try {
            repository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }

}