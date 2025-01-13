package com.example.demo.domains.enrollments;

import com.example.demo.domains.enrollments.dtos.EnrollmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    EnrollmentResponse toResponse(EnrollmentEntity enrollmentEntity);


}
