package com.example.demo.domains.classes;

import com.example.demo.domains.classes.dtos.ClassResponse;
import com.example.demo.domains.teachers.TeacherMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface ClassMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ClassResponse toResponse(ClassEntity classEntity, @Context ClassService classService);

    List<ClassResponse> toResponses(List<ClassEntity> classEntities, @Context ClassService classService);

    @AfterMapping
    default void updateResponse(final ClassEntity classEntity, @MappingTarget final ClassResponse classResponse, @Context ClassService classService) {
        classService.toResponse(classEntity.getClassId(), classResponse);
    }

    @AfterMapping
    default void updateListResponse(final List<ClassEntity> classEntities, @MappingTarget final List<ClassResponse> classResponses, @Context ClassService classService) {
        classService.toListResponse(classEntities.stream().map(ClassEntity::getClassId).toList(), classResponses);
    }
}
