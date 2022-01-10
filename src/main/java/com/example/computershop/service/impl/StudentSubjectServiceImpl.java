package com.example.computershop.service.impl;

import com.example.computershop.entities.School;
import com.example.computershop.entities.SubjectStudent;

import com.example.computershop.repository.SubjectStudentRepository;
import com.example.computershop.service.SubjectStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentSubjectServiceImpl implements SubjectStudentService {

    @Autowired
    private SubjectStudentRepository repository;

    @Override
    public List<SubjectStudent> getListSchool() {
        return repository.findAll();
    }

    @Override
    public Page<SubjectStudent> getAllSchoolForPagable(int page, int size) {
        return null;
    }

    @Override
    public Optional<SubjectStudent> getSubjectStuById(long id) {
        return repository.findById(id);
    }

    @Override
    public SubjectStudent save(SubjectStudent subjectStudent) {
        return repository.save(subjectStudent);
    }

    @Override
    public SubjectStudent update(SubjectStudent subjectStudent) {
        return null;
    }


    @Override
    public void deletedById(long id) {

    }
}
