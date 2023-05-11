package com.example.SpringAngular.repository;

import com.example.SpringAngular.entity.Student;
import com.example.SpringAngular.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, QuerydslPredicateExecutor<Student> {
}
