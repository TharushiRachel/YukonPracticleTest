package com.example.SpringAngular.entity;

import com.example.SpringAngular.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "student")
@EntityListeners(AuditingEntityListener.class)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private Status status;

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
