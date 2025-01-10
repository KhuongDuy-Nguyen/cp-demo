package com.example.demo.domains.enrollments.dtos;

import com.example.demo.domains.students.dtos.StudentResponse;
import com.example.demo.domains.subjects.dtos.SubjectResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponse {
    private long id;
    private StudentResponse student;
    private SubjectResponse subject;
}
