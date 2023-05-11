package com.example.SpringAngular.service;

import com.example.SpringAngular.entity.Student;

import java.util.List;

public interface StudentService {

    Student save(Student student) throws Exception;

    List<Student> getStudentList();

    Integer delete(int id);

    Student update(Student student) throws Exception;
}
