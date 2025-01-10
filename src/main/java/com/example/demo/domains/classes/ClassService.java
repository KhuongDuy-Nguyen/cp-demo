package com.example.demo.domains.classes;


import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.example.demo.domains.classes.dtos.ClassResponse;
import com.example.demo.domains.teachers.TeacherEntity;
import com.example.demo.domains.teachers.TeacherMapper;
import javax.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.MathArrays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClassService {
    private final CriteriaBuilderFactory criteriaBuilderFactory;
    private final ClassMapper classMapper;
    private final TeacherMapper teacherMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public Page<ClassResponse> getAllClasses(Pageable pageable) {
        var query = criteriaBuilderFactory.create(entityManager, ClassEntity.class, "cls");

        for (Sort.Order order : pageable.getSort()) {
            String property = order.getProperty();
            if (order.isAscending()) {
                query.orderByAsc("cls." + property);
            } else {
                query.orderByDesc("cls." + property);
            }
        }

        if (pageable.getSort().isEmpty()) {
            query.orderByAsc("cls.classId");
        }

        PagedList<ClassEntity> classes = query.page(pageable.getPageNumber(), pageable.getPageSize()).getResultList();
        List<ClassResponse> classResponses = classes.stream()
                .map(classMapper::toResponse)
                .collect(Collectors.toList());

        // Tạo PagedList mới với ClassResponse
        return new PageImpl<>(classResponses, pageable, classes.getTotalSize());
    }
}
