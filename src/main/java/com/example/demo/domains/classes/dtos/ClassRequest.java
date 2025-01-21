package com.example.demo.domains.classes.dtos;

import com.blazebit.persistence.CriteriaBuilder;
import com.example.demo.domains.classes.ClassEntity;
import com.example.demo.domains.teachers.TeacherEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassRequest {

  private String className;
  private List<Long> teacherIds;
  private List<String> teacherEmails;

  public CriteriaBuilder<ClassEntity> toPredicate(CriteriaBuilder<ClassEntity> query) {
    if (className != null) {
      query.where("cls.className").like().value("%" + className + "%").noEscape();
    }

    if (teacherIds != null && !teacherIds.isEmpty()) {
      query.where("cls.teacherId").in(teacherIds);
    }

    if (teacherEmails != null && !teacherEmails.isEmpty()) {
      query.innerJoinOn(TeacherEntity.class, "tch")
          .on("tch.teacherId").eqExpression("cls.teacherId").end()
          .where("tch.email").in(teacherEmails);
    }

    return query;
  }
}
