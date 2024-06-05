package com.avila.kopportunities.repository

import com.avila.kopportunities.model.Job

import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

import java.math.BigDecimal
import java.util.UUID

@Repository interface JobRepository : ListCrudRepository<Job, UUID> {
    fun findJobById(id: UUID?): Job?
    fun findAllByTitle(title: String?): List<Job>?
    fun findAllByDescription(description: String?): List<Job>?
    fun findAllByCompany(company: String?): List<Job>?
    fun findAllByLocation(location: String?): List<Job>?
    fun findAllByType(type: String?): List<Job>?
    fun findAllBySalary(salary: BigDecimal?): List<Job>?
    fun findAllByRequirements(requirements: String?): List<Job>?
    fun findAllByResponsibilities(responsibilities: String?): List<Job>?
}