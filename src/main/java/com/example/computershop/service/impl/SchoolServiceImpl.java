package com.example.computershop.service.impl;

import com.example.computershop.converter.SchoolConverter;
import com.example.computershop.dto.response.SchoolDTO;
import com.example.computershop.entities.School;
import com.example.computershop.entities.Student;
import com.example.computershop.repository.SchoolRepository;

import com.example.computershop.repository.StudentRepository;
import com.example.computershop.repository.custom.SchoolRepositoryCustom;
import com.example.computershop.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolConverter schoolConverter;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepositoryCustom repoCustom;

//    @Autowired
//    private SchoolStudentRepository schoolStudentRepository;

    @Override
    public List<SchoolDTO> getListSchool() {
      //  return schoolRepository.findAll().stream().map(s->schoolConverter.convertDecorSchool(s,studentRepository.getListStudentBySchoolId(s.getSchoolId()))).collect(Collectors.toList());
        return schoolRepository.findAll().stream().map(s->schoolConverter.convertDecorSchool(s)).collect(Collectors.toList());
    }

    @Override
    public Page<School> getAllSchoolForPagable(int page, int size) {
       return schoolRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Optional<School> getSchoolById(long id) {
        return schoolRepository.findById(id);
    }


    @Override
    public School save(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School update(School school) {
        return schoolRepository.save(school);
    }

    @Override
    @Transactional
    public void deletedById(long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public List<SchoolDTO> getAllSchoolPagingAndSort(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<SchoolDTO> pagedResult =new PageImpl<>(schoolRepository.findAll(paging).stream().map(x->schoolConverter.convertDecorSchool(x)).collect(Collectors.toList()));
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<SchoolDTO>();
        }

    }

//    @Override
//    public Page<SchoolStudentsResponse> getListPagination(int page, int size) {
//        return schoolStudentRepository.findAll(PageRequest.of(page,size));
//    }

}
