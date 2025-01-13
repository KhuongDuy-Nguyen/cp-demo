package com.example.demo.domains.students.dtos;

import com.example.demo.domains.classes.dtos.ClassResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {
    private Long studentId;
    private String studentName;
    private String email;
    private LocalDate dateOfBirth;
    private ClassResponse fromClass;
}
