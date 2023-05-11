package com.example.SpringAngular.Controller;

import com.example.SpringAngular.dto.request.TeacherCreateRequest;
import com.example.SpringAngular.dto.response.TeacherCreateResponse;
import com.example.SpringAngular.entity.Teacher;
import com.example.SpringAngular.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("${app.endpoint.teacherCreate}")
    public ResponseEntity<Object> saveTeacher(@Validated @RequestBody TeacherCreateRequest request) throws Exception{
        Teacher teacher = modelMapper.map(request, Teacher.class);
        Teacher saveTeacher = teacherService.save(teacher);
        TeacherCreateResponse teacherCreateResponse = modelMapper.map(saveTeacher, TeacherCreateResponse.class);
        return new ResponseEntity<>(teacherCreateResponse, HttpStatus.CREATED);
    }
}
