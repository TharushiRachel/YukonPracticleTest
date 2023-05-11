package com.example.SpringAngular.dto.response;

import com.example.SpringAngular.entity.Teacher;
import lombok.Data;

@Data
public class StudentViewResponse {

    private int id;

    private String name;

    private String surname;

    private Teacher teacher;
    @Data
    private static class Teacher{
        private String name;
    }
}
