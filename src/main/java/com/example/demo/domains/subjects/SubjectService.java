package com.example.demo.domains.subjects;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.KeysetPage;
import com.blazebit.persistence.PaginatedCriteriaBuilder;
import com.example.demo.domains.subjects.dtos.SubjectResponse;
import com.example.demo.domains.subjects.dtos.TeacherOfSubjectResponse;
import com.example.demo.domains.teachers.TeacherEntity;
import com.example.demo.domains.teachers.TeacherMapper;
import com.example.demo.domains.teachers.dtos.TeacherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author duy.nguyen
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectService {

  private final CriteriaBuilderFactory cbf;
  private final TeacherMapper teacherMapper;
  private final SubjectMapper subjectMapper;
  @PersistenceContext
  private EntityManager entityManager;

  public Page<TeacherOfSubjectResponse> getTeacherOfSubject(Pageable pageable) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    PaginatedCriteriaBuilder<Tuple> query = cbf.create(entityManager, Tuple.class)
        .from(SubjectEntity.class, "s")
        .select("s.teacherId", "teacherId")
        .select("JSON_ARRAYAGG(s.subjectId)", "subjectIds")
        .groupBy("s.teacherId")
        .orderByAsc("s.teacherId")
        .page(pageable.getPageNumber(), pageable.getPageSize());

    Map<Long, List<Long>> teacherSubjectMap = new HashMap<>();
    for (Tuple tuple : query.getResultList()) {
      Long teacherId = tuple.get("teacherId", Long.class);
      List<Long> subjectIds = objectMapper.readValue(tuple.get("subjectIds", String.class), new TypeReference<List<Long>>() {});
      teacherSubjectMap.put(teacherId, subjectIds);
    }

    List<TeacherEntity> teacherQuery = cbf.create(entityManager, TeacherEntity.class)
        .from(TeacherEntity.class, "s")
        .where("s.teacherId").in(teacherSubjectMap.keySet()).getResultList();

    List<SubjectEntity> subjectQuery = cbf.create(entityManager, SubjectEntity.class)
        .from(SubjectEntity.class, "s")
        .where("s.subjectId").in(new HashSet<>(teacherSubjectMap.values())).getResultList();

    List<TeacherOfSubjectResponse> responses = new ArrayList<>();
    teacherSubjectMap.forEach((teacherId, subjectIds) -> {
      TeacherEntity teacher = teacherQuery.stream().filter(t -> t.getTeacherId().equals(teacherId)).findFirst().orElse(null);
      List<SubjectEntity> subjects = subjectQuery.stream().filter(s -> subjectIds.contains(s.getSubjectId())).toList();
      TeacherResponse teacherResponse = teacherMapper.toResponse(teacher);
      List<SubjectResponse> subjectResponses = subjects.stream().map(subjectMapper::toResponse).toList();
      responses.add(new TeacherOfSubjectResponse(teacherResponse, subjectResponses));
    });
    return new PageImpl<>(responses, pageable, responses.size());
  }
}
