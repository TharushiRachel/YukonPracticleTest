package com.example.SpringAngular.service.impl;

import com.example.SpringAngular.entity.QStudent;
import com.example.SpringAngular.entity.Student;
import com.example.SpringAngular.entity.Teacher;
import com.example.SpringAngular.enums.Status;
import com.example.SpringAngular.exception.ApiRequestException;
import com.example.SpringAngular.repository.StudentRepository;
import com.example.SpringAngular.repository.TeacherRepository;
import com.example.SpringAngular.service.StudentService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Student save(Student student) throws Exception {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QStudent.student.name.eq(student.getName()));
        List<Student> students = (List<Student>) studentRepository.findAll(booleanBuilder);

        if(students.size() > 0){
            throw new ApiRequestException("Student Already Exists");
        }

        Teacher teacherDb = teacherRepository.findById(student.getTeacher().getId()).orElseThrow(() ->{
            throw new ApiRequestException("Teacher with " +student.getTeacher().getId()+ "does not exists");
        });

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Integer delete(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new ApiRequestException("Student with " + id + " does not exists");
        });

        student.setStatus(Status.DELETED);
        return student.getId();
    }

    @Override
    public Student update(Student student) throws Exception {
        Student studentDb = studentRepository.findById(student.getId()).orElseThrow(()->{
            throw new ApiRequestException("Student with "+student.getId()+"does not exists");
        });

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QStudent.student.name.eq(student.getName()));
        List<Student> students = (List<Student>) studentRepository.findAll(booleanBuilder);

        if(students.size() > 1){
            throw new ApiRequestException("Student already exists");
        }

        student.setStatus(Status.valueOf("ACTIVE"));

        studentDb.setName(student.getName());
        studentDb.setSurname(student.getSurname());
        studentDb.setTeacher(student.getTeacher());

        return studentRepository.save(studentDb);
    }
}
