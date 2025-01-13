package com.example.demo.domains.enrollments;

import com.example.demo.domains.enrollments.dtos.EnrollmentRequest;
import com.example.demo.domains.enrollments.dtos.EnrollmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping()
    public List<EnrollmentResponse> search(EnrollmentRequest request, Pageable pageable) {
        return enrollmentService.get(request, pageable);
    }
}
