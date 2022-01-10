package com.example.computershop.repository.custom;

import com.example.computershop.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentRepositoryCustom {
    List<Student> getPagination(String fileName, Date dateFrom, Date dateTo, int size, int page, String orderBy, boolean desc);
    long getCount(String fileName, Date dateFrom, Date dateTo, int size, int page, String orderBy, boolean desc);
}
