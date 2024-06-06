package com.avila.kopportunities.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrePersist
import jakarta.persistence.Table

import java.time.LocalDate
import java.util.UUID

@Table(name = "applications")
@Entity class Application(
    @ManyToOne val job: Job,
    val applicantName: String,
    val applicantEmail: String,
    val resumeUrl: String,
    val coverLetter: String
) {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id var id: UUID? = null

    private var appliedDate: LocalDate? = null

    private var status: String? = null

    @PrePersist
    private fun generateValues() {
        this.status = "Submitted"
        this.appliedDate = LocalDate.now()
    }

    fun build() =
        ApplicationResponseDTO(
            id = this.id,
            jobId = this.job.id,
            appliedDate = this.appliedDate,
            status = this.status,
            applicantName = this.applicantName,
            applicantEmail = this.applicantEmail,
            resumeUrl = this.resumeUrl,
            coverLetter = this.coverLetter
        )

}

data class ApplicationResponseDTO(
    val id: UUID?,
    var appliedDate: LocalDate?,
    var status: String?,
    val jobId: UUID?,
    val applicantName: String,
    val applicantEmail: String,
    val resumeUrl: String,
    val coverLetter: String
)

data class ApplicationRequestDTO(
    val applicantName: String,
    val applicantEmail: String,
    val resumeUrl: String,
    val coverLetter: String
)