package com.example.SpringAngular.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class TeacherCreateRequest {

    @NotNull
//    @Size(min = 2, max = 50, message = "The name should have atleast 2 characters")
    private String name;
}
