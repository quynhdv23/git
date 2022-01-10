package com.example.computershop.converter;

import com.example.computershop.dto.response.SchoolDTO;
import com.example.computershop.entities.School;
import com.example.computershop.entities.Student;
import com.example.computershop.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchoolConverter {
    @Autowired
    private SchoolService schoolService;



//    public SchoolDTO convertToDTO(School school){
//        SchoolDTO dto=modelMapper.map(school,SchoolDTO.class);
//        return dto;
//    }

    public SchoolDTO convertDecorSchool(School school){
        SchoolDTO dto= new SchoolDTO();
        dto.setSchoolId(school.getSchoolId());
        dto.setSchoolName(school.getSchoolName());
        dto.setSchoolAddress(school.getSchoolAddress());
        dto.setContactEmail(school.getContactEmail());
        dto.setContactPhone(school.getContactPhone());
        dto.setListStudent(school.getStudents().stream().map(x -> {
            Student student=new Student();
            student.setStudentId(x.getStudentId());
            student.setFullName(x.getFullName());
            student.setGender(x.getGender());
            student.setBirthday(x.getBirthday());
            student.setAddress(x.getAddress());
            student.setPhone(x.getPhone());
            student.setEmail(x.getEmail());
            student.setSubjects(x.getSubjects());
            student.setCreatedAt(x.getCreatedAt());
            student.setUpdatedAt(x.getUpdatedAt());
            student.setDeletedAt(x.getDeletedAt());
            return student;
        }).collect(Collectors.toList()));
        return dto;
    }
}

