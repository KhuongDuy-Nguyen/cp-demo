package com.example.demo.domains.students;

import com.example.demo.domains.students.dtos.StudentResponse;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  StudentResponse toResponse(StudentEntity studentEntity, @Context StudentService studentService);

  List<StudentResponse> toListResponse(List<StudentEntity> studentEntities, @Context StudentService studentService);

  @AfterMapping
  default void updateResponse(final StudentEntity studentEntity, @MappingTarget final StudentResponse studentResponse,
      @Context StudentService studentService) {
    studentService.toResponse(studentEntity.getStudentId(), studentResponse);
  }

  @AfterMapping
  default void updateListResponse(final List<StudentEntity> studentEntities,
      @MappingTarget final List<StudentResponse> studentResponses, @Context StudentService studentService) {
    studentService.toListResponse(studentEntities.stream().map(StudentEntity::getStudentId).toList(), studentResponses);
  }
}
