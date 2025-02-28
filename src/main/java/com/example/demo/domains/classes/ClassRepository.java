package com.example.demo.domains.classes;

import com.example.demo.domains.enrollments.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<EnrollmentEntity, Long> {

}