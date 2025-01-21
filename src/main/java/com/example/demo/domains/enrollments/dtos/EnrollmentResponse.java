package com.example.demo.domains.enrollments.dtos;

import com.example.demo.domains.students.dtos.StudentResponse;
import com.example.demo.domains.subjects.dtos.SubjectResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponse {

  private Long enrollmentId;
  private SubjectResponse subject;
  private List<StudentResponse> students;
  private int totalStudents;
  private LocalDate enrollmentDate;
}
