package com.example.SpringAngular.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "teacher")
@EntityListeners(AuditingEntityListener.class)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "teacher", targetEntity = Student.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Student> students;
}
