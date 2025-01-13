package com.example.demo.domains.classes;

import com.example.demo.domains.classes.dtos.ClassRequest;
import com.example.demo.domains.classes.dtos.ClassResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class")
public class ClassController {
    private final ClassService classService;

    @GetMapping()
    public Page<ClassResponse> search(ClassRequest request, Pageable pageable) {
        return classService.get(request, pageable);
    }

}
