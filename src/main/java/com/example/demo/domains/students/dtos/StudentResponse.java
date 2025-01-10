package com.example.demo.domains.students.dtos;

import com.example.demo.domains.classes.dtos.ClassResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private long id;
    private String name;
    private String email;
    private ClassResponse fromClass;
}
