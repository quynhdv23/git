package com.example.computershop.service.impl;

import com.example.computershop.dto.response.StudentDTO;
import com.example.computershop.entities.Student;
import com.example.computershop.repository.custom.StudentRepositoryCustom;
import com.example.computershop.service.StudentServiceCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceCustomImpl implements StudentServiceCustom {

    @Autowired
    private StudentRepositoryCustom stuRepo;

    @Override
    public List<StudentDTO> getListStudentPaging() {
        return null;
    }
}
