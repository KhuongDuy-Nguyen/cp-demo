package com.example.demo.domains.classes;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

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
