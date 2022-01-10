package com.example.computershop.service;

import com.example.computershop.entities.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getListStudent();
    Page<Student> getAllStudentForPagable(int page, int size);

    Optional<Student> getStudentById(long id);
    Student save(Student Student);
    Student update(Student Student);
    void deletedById(long id);
    List<Student> getListStudent(Long schoolId);


}
