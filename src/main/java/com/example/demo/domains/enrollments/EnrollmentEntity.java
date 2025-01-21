package com.example.demo.domains.enrollments;


import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Getter
@Setter
@Table(name = "enrollment", schema = "public")
public class EnrollmentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long enrollmentId;

  private Long studentId;

  private Long subjectId;

  private LocalDate enrollmentDate;
}
