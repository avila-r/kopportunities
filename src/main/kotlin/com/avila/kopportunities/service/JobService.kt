package com.avila.kopportunities.service

import com.avila.kopportunities.model.Job
import com.avila.kopportunities.repository.JobRepository

import jakarta.transaction.Transactional

import org.springframework.stereotype.Service

import java.util.UUID

@Service class JobService( private val repository: JobRepository ) {

    @Transactional fun insertJob(job: Job?): Job {
        return job?.let {
            repository.save(it)
        } ?: throw Exception() // Todo: custom exception
    }

    fun getJob(id: UUID): Job {
        repository.findJobById(id)?.let {
            return it
        } ?: throw Exception() // Todo: custom exception
    }

}