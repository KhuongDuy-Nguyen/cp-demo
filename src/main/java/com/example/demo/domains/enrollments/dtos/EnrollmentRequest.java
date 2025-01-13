package com.example.demo.domains.enrollments.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EnrollmentRequest {
    private List<Long> studentIds;
    private List<Long> subjectIds;
    private LocalDate enrollmentDate;
}
