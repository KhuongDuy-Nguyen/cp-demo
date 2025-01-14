package com.example.demo.domains.teachers.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponse {
    private long teacherId;
    private String teacherName;
    private String email;
    private int age;
    private String ageType;
}
