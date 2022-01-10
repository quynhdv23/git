package com.example.computershop.service;

import com.example.computershop.dto.response.StudentDTO;
import com.example.computershop.entities.Student;

import java.util.List;

public interface StudentServiceCustom {
    List<StudentDTO> getListStudentPaging();
}
