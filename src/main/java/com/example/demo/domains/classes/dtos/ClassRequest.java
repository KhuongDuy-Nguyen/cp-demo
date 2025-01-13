package com.example.demo.domains.classes.dtos;

import com.blazebit.persistence.CriteriaBuilder;
import com.example.demo.domains.classes.ClassEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClassRequest {
    private String className;
    private List<Long> teacherIds;

    public CriteriaBuilder<ClassEntity> toPredicate(CriteriaBuilder<ClassEntity> query) {
        if (className != null && !className.isEmpty()) {
            query.where("cls.className").like().value("%" + className + "%").noEscape();
        }

        if (teacherIds != null && !teacherIds.isEmpty()) {
            query.where("cls.teacherId").in(teacherIds);
        }
        return query;
    }
}
