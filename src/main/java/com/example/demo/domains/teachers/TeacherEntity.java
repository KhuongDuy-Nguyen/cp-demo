package com.example.demo.domains.teachers;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

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
}
