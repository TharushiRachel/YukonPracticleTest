package com.example.SpringAngular.Controller;

import com.example.SpringAngular.dto.request.StudentCreateRequest;
import com.example.SpringAngular.dto.request.StudentUpdateRequest;
import com.example.SpringAngular.dto.response.StudentCreateResponse;
import com.example.SpringAngular.dto.response.StudentDeleteResponse;
import com.example.SpringAngular.dto.response.StudentUpdateResponse;
import com.example.SpringAngular.dto.response.StudentViewResponse;
import com.example.SpringAngular.entity.Student;
import com.example.SpringAngular.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("${app.endpoint.studentCreate}")
    public ResponseEntity<StudentCreateResponse> saveStudent(@Validated @RequestBody StudentCreateRequest request) throws Exception{
        Student student = modelMapper.map(request, Student.class);
        Student saveStudent = studentService.save(student);
        StudentCreateResponse studentCreateResponse = modelMapper.map(saveStudent, StudentCreateResponse.class);
        return new ResponseEntity<>(studentCreateResponse, HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.studentView}")
    public ResponseEntity<List<StudentViewResponse>> viewStudents(){
        List<Student> studentList = studentService.getStudentList();
        List<StudentViewResponse> studentViewResponses = studentList.stream().map(student -> modelMapper.map(student, StudentViewResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(studentViewResponses, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("$(app.endpoint.studentUpdate)")
    public ResponseEntity<StudentUpdateResponse> updateStudent(@PathVariable int id, @Validated @RequestBody StudentUpdateRequest request) throws Exception{
        Student student = modelMapper.map(request, Student.class);
        student.setId(id);
        Student studentUpdate = studentService.update(student);
        StudentUpdateResponse studentUpdateResponse = modelMapper.map(studentUpdate, StudentUpdateResponse.class);
        return new ResponseEntity<>(studentUpdateResponse, HttpStatus.OK);
    }


    @DeleteMapping("${app.endpoint.studentDelete}")
    public ResponseEntity<StudentDeleteResponse> delete(@PathVariable int id){
        Integer deletedStudentId = studentService.delete(id);
        StudentDeleteResponse studentDeleteResponse = new StudentDeleteResponse();
        studentDeleteResponse.setId(deletedStudentId);
        return new ResponseEntity<>(studentDeleteResponse, HttpStatus.OK);
    }
}
