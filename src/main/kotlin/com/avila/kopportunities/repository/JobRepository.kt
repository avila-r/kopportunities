package com.avila.kopportunities.repository

import com.avila.kopportunities.model.Job

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import java.util.UUID

@Repository interface JobRepository : CrudRepository<Job, UUID> {
    fun findJobById(id: UUID?): Job?
}