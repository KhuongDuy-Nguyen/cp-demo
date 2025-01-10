package com.example.demo.domains.subjects.dtos;

import com.example.demo.domains.teachers.dtos.TeacherResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {
    private long id;
    private String name;
    private TeacherResponse teacher;
}
