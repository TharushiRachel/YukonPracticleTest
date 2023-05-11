package com.example.SpringAngular.service;

import com.example.SpringAngular.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher save(Teacher teacher) throws Exception;

    List<Teacher> getTeacherList();
}
