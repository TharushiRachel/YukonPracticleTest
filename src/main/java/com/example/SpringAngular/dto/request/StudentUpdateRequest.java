package com.example.SpringAngular.dto.request;

import lombok.Data;

@Data
public class StudentUpdateRequest {

    private String name;

    private String surname;

    private Teacher teacher;
    @Data
    private static class Teacher {
        private int id;
    }

}
