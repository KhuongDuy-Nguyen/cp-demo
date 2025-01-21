/**
 * Pharmacy Stars, LLC. Copyright (c) 2023‐2024 All Rights Reserved.
 */
package com.example.demo.domains.subjects.dtos;

import com.example.demo.domains.teachers.dtos.TeacherResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author duy.nguyen
 */
@Getter
@Setter
@AllArgsConstructor
public class TeacherOfSubjectResponse {
  private TeacherResponse teacher;
  private List<SubjectResponse> subjects;
}
