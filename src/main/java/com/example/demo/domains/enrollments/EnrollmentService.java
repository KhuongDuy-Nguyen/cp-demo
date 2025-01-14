package com.example.demo.domains.enrollments;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.example.demo.domains.enrollments.dtos.EnrollmentRequest;
import com.example.demo.domains.enrollments.dtos.EnrollmentResponse;
import com.example.demo.domains.students.StudentEntity;
import com.example.demo.domains.students.StudentMapper;
import com.example.demo.domains.students.StudentService;
import com.example.demo.domains.students.dtos.StudentResponse;
import com.example.demo.domains.subjects.SubjectEntity;
import com.example.demo.domains.subjects.SubjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnrollmentService {
    private final StudentService studentService;
    private final CriteriaBuilderFactory cbf;
    private final EnrollmentMapper enrollmentMapper;
    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public List<EnrollmentResponse> get(EnrollmentRequest request, Pageable pageable) {
        CriteriaBuilder<Tuple> tupleCriteriaBuilder = cbf.create(entityManager, Tuple.class)
                .from(EnrollmentEntity.class, "enr")
                .leftJoinOn(StudentEntity.class, "stu").on("enr.studentId").eqExpression("stu.studentId").end()
                .leftJoinOn(SubjectEntity.class, "sub").on("enr.subjectId").eqExpression("sub.subjectId").end()
                .select("enr", "enrollment")
                .select("stu", "student")
                .select("sub", "subject")
                .selectSubquery("studentCount")
                    .from(EnrollmentEntity.class, "subEnr")
                    .select("COUNT(subEnr.studentId)")
                    .where("subEnr.subjectId").eqExpression("sub.subjectId")
                .end()
                .groupBy("enr.enrollmentId", "stu.studentId", "sub.subjectId");

        List<Tuple> tuples = request.toQuery(tupleCriteriaBuilder).getResultList();

        Map<Long, List<StudentResponse>> studentOfSubject = new HashMap<>();
        List<EnrollmentResponse> responses = new ArrayList<>();

        for (Tuple tuple : tuples) {
            EnrollmentEntity enrollmentEntity = tuple.get("enrollment", EnrollmentEntity.class);
            StudentEntity studentEntity = tuple.get("student", StudentEntity.class);
            SubjectEntity subjectEntity = tuple.get("subject", SubjectEntity.class);

            Long totalStudents = tuple.get(3, Long.class);
            EnrollmentResponse response = enrollmentMapper.toResponse(enrollmentEntity);

            studentOfSubject.computeIfAbsent(subjectEntity.getSubjectId(), k -> new ArrayList<>())
                    .add(studentMapper.toResponse(studentEntity, studentService));
            response.setSubject(subjectMapper.toResponse(subjectEntity));
            response.setTotalStudents(totalStudents.intValue());
            responses.add(response);
        }

        for (EnrollmentResponse response : responses) {
            response.setStudents(studentOfSubject.get(response.getSubject().getSubjectId()));
        }

        return responses;
    }
}
