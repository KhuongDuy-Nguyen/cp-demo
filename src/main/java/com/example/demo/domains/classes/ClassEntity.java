package com.example.demo.domains.classes;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Access(AccessType.FIELD)
@Entity
@Getter
@Setter
@ToString
@Table(name = "class", schema = "public")
public class ClassEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long classId;

  private String className;

  private Long teacherId;
}
