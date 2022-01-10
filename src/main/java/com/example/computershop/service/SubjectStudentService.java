package com.example.computershop.service;

import com.example.computershop.entities.SubjectStudent;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SubjectStudentService {
    List<SubjectStudent> getListSchool();
    Page<SubjectStudent> getAllSchoolForPagable(int page, int size);

    Optional<SubjectStudent> getSubjectStuById(long id);
    SubjectStudent save(SubjectStudent subjectStudent);
    SubjectStudent update(SubjectStudent subjectStudent);
    void deletedById(long id);
}
