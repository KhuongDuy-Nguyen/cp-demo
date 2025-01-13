package com.example.demo.domains.classes;


import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.example.demo.domains.classes.dtos.ClassRequest;
import com.example.demo.domains.classes.dtos.ClassResponse;
import com.example.demo.domains.teachers.TeacherEntity;
import com.example.demo.domains.teachers.TeacherMapper;
import com.example.demo.domains.teachers.dtos.TeacherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClassService {
    private final CriteriaBuilderFactory criteriaBuilderFactory;
    private final ClassMapper classMapper;
    private final TeacherMapper teacherMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public Page<ClassResponse> get(ClassRequest request, Pageable pageable) {
        var query = criteriaBuilderFactory.create(entityManager, ClassEntity.class, "cls");
        var teacherQuery = criteriaBuilderFactory.create(entityManager, TeacherEntity.class, "tch");

        PagedList<ClassEntity> classes = request.toPredicate(query)
                .orderByAsc("cls.classId")
                .page(pageable.getPageNumber(), pageable.getPageSize())
                .getResultList();

        List<TeacherEntity> teachers = teacherQuery
                .where("tch.teacherId").in(classes.stream().map(ClassEntity::getTeacherId).toList())
                .getResultList();

        List<ClassResponse> classResponses = classes.stream()
                .map(classEntity -> {
                    TeacherEntity teacher = teachers.stream()
                            .filter(teacherEntity -> teacherEntity.getTeacherId().equals(classEntity.getTeacherId()))
                            .findAny().orElse(null);
                    ClassResponse classResponse = classMapper.toResponse(classEntity);
                    TeacherResponse teacherResponse = teacher != null ? teacherMapper.toResponse(teacher) : null;
                    classResponse.setTeacher(teacherResponse);
                    return classResponse;
                })
                .toList();

        return new PageImpl<>(classResponses, pageable, classes.getTotalSize());
    }
}
