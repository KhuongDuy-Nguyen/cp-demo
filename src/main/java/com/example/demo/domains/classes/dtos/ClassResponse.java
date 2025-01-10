package com.example.demo.domains.classes.dtos;

import com.example.demo.domains.teachers.dtos.TeacherResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassResponse {
    private long id;
    private String name;
    private TeacherResponse teacher;
}
