package com.example.demo.domains.teachers;

import com.example.demo.domains.teachers.dtos.TeacherRequest;
import com.example.demo.domains.teachers.dtos.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping()
    public Page<TeacherResponse> search(TeacherRequest request, Pageable pageable) {
        return teacherService.get(request, pageable);
    }
}
