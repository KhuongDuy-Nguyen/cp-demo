package com.example.demo.domains.subjects;

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
@Table(name = "subject", schema = "public")
public class SubjectEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long subjectId;

  private String subjectName;

  private Long teacherId;
}