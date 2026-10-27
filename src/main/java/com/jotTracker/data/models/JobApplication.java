package com.jotTracker.data.models;

import com.jotTracker.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
@Data
public class JobApplication {
    @Id
    @SequenceGenerator(
            name = "jobApplication_sequence",
            sequenceName = "jobApplication_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "jobApplication_sequence"
    )
    private Long id;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    private String companyName;

    @NotNull(message = "Application date is required")
    @PastOrPresent(message = "Application date cannot be in the future")
    private LocalDate applicationDate;

    @NotNull(message = "Application status is required")
    private ApplicationStatus status;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull(message = "User is required")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
