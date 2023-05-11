package com.example.SpringAngular.repository;

import com.example.SpringAngular.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>, QuerydslPredicateExecutor<Teacher> {
}
