package com.example.computershop.service;

import com.example.computershop.dto.response.SchoolDTO;
import com.example.computershop.entities.School;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface SchoolService {
    List<SchoolDTO> getListSchool();
    Page<School> getAllSchoolForPagable(int page, int size);
    Optional<School> getSchoolById(long id);
    School save(School school);
    School update(School school);
    void deletedById(long id);
    List<SchoolDTO> getAllSchoolPagingAndSort(Integer pageNo, Integer pageSize, String sortBy);
//    Page<SchoolStudentsResponse> getListPagination(int page, int size);

}
