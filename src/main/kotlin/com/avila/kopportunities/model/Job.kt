package com.avila.kopportunities.model

import com.avila.kopportunities.controller.JobController.Response

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.Table

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

import org.springframework.data.annotation.CreatedDate

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Table(name = "jobs")
@Entity class Job (

    @Column(name = "title", nullable = false)
    @NotBlank
    val title: String,

    @Column(name = "description", nullable = false)
    @NotBlank
    val description: String,

    @Column(name = "company", nullable = false)
    @NotBlank
    val company: String,

    @Column(name = "location", nullable = false)
    @NotBlank
    val location: String,

    @Column(name = "type", nullable = false)
    @NotBlank
    val type: String,

    @Column(name = "salary", nullable = false)
    @NotNull
    val salary: BigDecimal,

    @Column(name = "requirements", nullable = false)
    @NotBlank
    val requirements: String,

    @Column(name = "responsibilities", nullable = false)
    @NotBlank
    val responsibilities: String
) {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id var id: UUID? = null

    @Column(name = "posted_date", nullable = false)
    @CreatedDate
    var lastModified: LocalDate? = null

    @Column(name = "status", nullable = false)
    var status: String? = null

    @PrePersist
    fun defineValues() {
        this.status = "Hiring"
        this.lastModified = LocalDate.now()
    }

    fun build():Response =
        Response(
            id = this.id,
            lastModified = this.lastModified,
            status = this.status,
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