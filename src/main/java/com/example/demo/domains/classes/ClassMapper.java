package com.example.demo.domains.classes;

import com.example.demo.domains.classes.dtos.ClassResponse;
import com.example.demo.domains.teachers.TeacherMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface ClassMapper {
    @Mapping(source = "classId", target = "id")
    @Mapping(source = "className", target = "name")
    ClassResponse toResponse(ClassEntity classEntity);

    List<ClassResponse> toResponses(List<ClassEntity> classEntities);
}
