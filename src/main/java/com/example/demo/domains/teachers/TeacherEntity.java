package com.example.demo.domains.teachers;

import java.time.LocalDate;
import javax.persistence.Column;
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
@Table(name = "teacher", schema = "public")
public class TeacherEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long teacherId;

  private String teacherName;

  @Column(unique = true)
  private String email;

  private LocalDate hireDate;

  private int age;
}
