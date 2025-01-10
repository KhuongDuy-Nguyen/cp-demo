package com.example.demo.domains.classes;

import com.example.demo.domains.classes.dtos.ClassResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class")
public class ClassController {
    private final ClassService classService;

    @GetMapping("/all")
    public Page<ClassResponse> getAllClasses(Pageable pageable) {
        return classService.getAllClasses(pageable);
    }

}
