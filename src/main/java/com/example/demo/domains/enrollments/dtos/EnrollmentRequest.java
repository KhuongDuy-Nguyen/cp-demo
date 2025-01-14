package com.example.demo.domains.enrollments.dtos;

import com.blazebit.persistence.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EnrollmentRequest {
    private List<Long> studentIds;
    private List<Long> subjectIds;
    private Long studentCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    public CriteriaBuilder<Tuple> toQuery(CriteriaBuilder<Tuple> criteriaBuilder) {
        if (studentIds != null && !studentIds.isEmpty() && studentCount > 0) {
            criteriaBuilder
                    .whereOr()
                    .where("enr.studentId").isNull()
                    .where("enr.studentId").in(studentIds)
                    .endOr();
        }

        if (subjectIds != null && !subjectIds.isEmpty()) {
            criteriaBuilder
                    .whereOr()
                    .where("enr.subjectId").isNull()
                    .whereAnd()
                    .where("enr.subjectId").in(subjectIds)
                    .where("studentCount").eq(studentCount)
                    .endAnd()
                    .endOr();
        }

        if (fromDate != null) {
            criteriaBuilder
                    .whereOr()
                    .where("enr.enrollmentDate").isNull()
                    .where("enr.enrollmentDate").ge(fromDate)
                    .endOr();
        }

        if (toDate != null) {
            criteriaBuilder
                    .whereOr()
                    .where("enr.enrollmentDate").isNull()
                    .where("enr.enrollmentDate").le(toDate)
                    .endOr();
        }
        return criteriaBuilder;
    }
}
