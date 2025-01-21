package com.example.demo.domains.teachers;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.example.demo.domains.teachers.dtos.TeacherRequest;
import com.example.demo.domains.teachers.dtos.TeacherResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

  private final CriteriaBuilderFactory cbf;
  private final TeacherMapper teacherMapper;
  @PersistenceContext
  private EntityManager entityManager;

  public Page<TeacherResponse> get(TeacherRequest request, Pageable pageable) {
    List<Tuple> tuples = cbf.create(entityManager, Tuple.class)
        .from(TeacherEntity.class, "tch")
        .select("tch", "teacher")
        .selectCase("type")
        .when("tch.age").lt(30).then("Young")
        .when("tch.age").lt(50).then("Middle")
        .otherwise("Old")
        .orderByDesc("tch.teacherId").getResultList();

    List<TeacherResponse> responses = new ArrayList<>();
    for (Tuple tuple : tuples) {
      TeacherEntity teacherEntity = tuple.get("teacher", TeacherEntity.class);
      String ageType = tuple.get("type", String.class);

      TeacherResponse response = teacherMapper.toResponse(teacherEntity);
      response.setAgeType(ageType);

      responses.add(response);
    }

    return new PageImpl<>(responses, pageable, responses.size());
  }

}
