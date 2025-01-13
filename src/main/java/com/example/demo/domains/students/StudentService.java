package com.example.demo.domains.students;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.example.demo.domains.classes.ClassEntity;
import com.example.demo.domains.classes.ClassMapper;
import com.example.demo.domains.classes.ClassService;
import com.example.demo.domains.classes.QClassEntity;
import com.example.demo.domains.students.dtos.StudentResponse;
import com.querydsl.core.group.GroupBy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final ClassService classService;
    private final CriteriaBuilderFactory criteriaBuilderFactory;
    private final ClassMapper classMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public void toResponse(Long studentId, StudentResponse response) {
        QStudentEntity qStudent = QStudentEntity.studentEntity;
        QClassEntity qClass = QClassEntity.classEntity;

        Map<StudentEntity, ClassEntity> subjectByStudent = new BlazeJPAQuery<>(entityManager, criteriaBuilderFactory)
                .from(qStudent).where(qStudent.studentId.eq(studentId))
                .innerJoin(qClass).on(qClass.classId.eq(qStudent.classId))
                .transform(GroupBy.groupBy(qStudent).as(qClass));

        StudentEntity studentEntity = subjectByStudent.keySet().stream().toList().get(0);
        response.setFromClass(classMapper.toResponse(subjectByStudent.get(studentEntity), classService));
    }

    public void toListResponse(List<Long> studentIds, List<StudentResponse> responses) {
        QStudentEntity qStudent = QStudentEntity.studentEntity;
        QClassEntity qClass = QClassEntity.classEntity;

        Map<StudentEntity, ClassEntity> subjectByStudent = new BlazeJPAQuery<>(entityManager, criteriaBuilderFactory)
                .from(qStudent).where(qStudent.studentId.in(studentIds))
                .innerJoin(qClass).on(qClass.classId.eq(qStudent.classId))
                .transform(GroupBy.groupBy(qStudent).as(qClass));

        subjectByStudent.forEach((studentEntity, classEntity) -> {
            responses.stream()
                    .filter(studentResponse -> studentResponse.getStudentId().equals(studentEntity.getStudentId()))
                    .findAny().ifPresent(response -> response.setFromClass(classMapper.toResponse(classEntity, classService)));
        });
    }
}
