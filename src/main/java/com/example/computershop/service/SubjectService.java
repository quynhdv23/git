package com.example.computershop.service;

import com.example.computershop.entities.Subject;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<Subject> getListSubject();
    Page<Subject> getAllSubjectForPagable(int page, int size);

    Optional<Subject> getSubjectById(long id);
    Subject save(Subject subject);
    Subject update(Subject subject);
    void deletedById(long id);


}
