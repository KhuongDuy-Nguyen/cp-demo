package com.example.demo.domains.students.dtos;

import com.example.demo.domains.classes.dtos.ClassResponse;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

  private Long studentId;
  private String studentName;
  private String email;
  private LocalDate dateOfBirth;
  private ClassResponse fromClass;
}
