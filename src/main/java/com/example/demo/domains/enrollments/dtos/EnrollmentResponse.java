package com.example.demo.domains.enrollments.dtos;

import com.example.demo.domains.students.dtos.StudentResponse;
import com.example.demo.domains.subjects.dtos.SubjectResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnrollmentResponse {
    private Long enrollmentId;
    private SubjectResponse subject;
    private List<StudentResponse> students;
    private int totalStudents;
}
