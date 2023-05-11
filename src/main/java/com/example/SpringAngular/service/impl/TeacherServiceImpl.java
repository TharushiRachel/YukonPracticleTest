package com.example.SpringAngular.service.impl;

import com.example.SpringAngular.entity.QTeacher;
import com.example.SpringAngular.entity.Teacher;
import com.example.SpringAngular.exception.ApiRequestException;
import com.example.SpringAngular.repository.TeacherRepository;
import com.example.SpringAngular.service.TeacherService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;

import java.util.List;


@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public Teacher save(Teacher teacher) throws Exception {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QTeacher.teacher.name.eq(teacher.getName()));
        List<Teacher> teachers= (List<Teacher>) teacherRepository.findAll(booleanBuilder);

        if(teachers.size()>0){
            throw new ApiRequestException("Teacher Already Exists");
        }

        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> getTeacherList() {
        return teacherRepository.findAll();
    }
}
