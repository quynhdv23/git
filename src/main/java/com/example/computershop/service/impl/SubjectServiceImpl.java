package com.example.computershop.service.impl;

import com.example.computershop.entities.Subject;

import com.example.computershop.repository.SubjectRepository;
import com.example.computershop.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository repo;

    @Override
    public List<Subject> getListSubject() {
        return repo.findAll();
    }

    @Override
    public Page<Subject> getAllSubjectForPagable(int page, int size) {

        return repo.findAll(PageRequest.of(page,size));
    }

    @Override
    public Optional<Subject> getSubjectById(long id) {

        return repo.findById(id);
    }

    @Override
    public Subject save(Subject subject) {
        return repo.save(subject);
    }

    @Override
    public Subject update(Subject subject) {
        return repo.save(subject);
    }

    @Override
    public void deletedById(long id) {
        repo.deleteById(id);
    }
}
