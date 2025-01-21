/**
 * Pharmacy Stars, LLC. Copyright (c) 2023‐2024 All Rights Reserved.
 */
package com.example.demo.domains.subjects;

import com.example.demo.domains.subjects.dtos.TeacherOfSubjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duy.nguyen
 */
@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

  private final SubjectService subjectService;
  // Get teacher of subject

  @GetMapping("/teacher")
  public List<TeacherOfSubjectResponse> getTeacherOfSubject(Pageable pageable) throws JsonProcessingException {
    return subjectService.getTeacherOfSubject(pageable);
  }
}
