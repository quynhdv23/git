package com.example.computershop.repository.custom;

import com.example.computershop.entities.School;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepositoryCustom extends PagingAndSortingRepository<School,Long> {

}
