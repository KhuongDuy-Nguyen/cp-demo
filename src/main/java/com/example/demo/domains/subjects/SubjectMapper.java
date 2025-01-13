package com.example.demo.domains.subjects;

import com.example.demo.domains.subjects.dtos.SubjectResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectResponse toResponse(SubjectEntity subjectEntity);
}
