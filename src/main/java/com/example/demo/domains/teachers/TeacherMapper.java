package com.example.demo.domains.teachers;

import com.example.demo.domains.classes.ClassEntity;
import com.example.demo.domains.classes.dtos.ClassResponse;
import com.example.demo.domains.teachers.dtos.TeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(source = "teacherId", target = "id")
    @Mapping(source = "teacherName", target = "name")
    TeacherResponse toResponse(TeacherEntity teacherEntity);
}
