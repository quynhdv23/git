package com.example.computershop.service.impl;

import com.example.computershop.entities.Student;
import com.example.computershop.repository.StudentRepository;
import com.example.computershop.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;



    @Override
    public List<Student> getListStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> getAllStudentForPagable(int page, int size) {
        // phân trang bằng tay ý
        return studentRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deletedById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getListStudent(Long schoolId) {
        return studentRepository.getListStudentBySchoolId(schoolId);
    }
}
