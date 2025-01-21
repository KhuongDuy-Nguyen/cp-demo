package com.example.demo.domains.subjects.dtos;

import com.example.demo.domains.teachers.dtos.TeacherResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {

  private Long subjectId;
  private String subjectName;
  private TeacherResponse teacher;
}
