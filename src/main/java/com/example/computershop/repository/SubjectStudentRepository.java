package com.example.computershop.repository;

import com.example.computershop.entities.SubjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectStudentRepository extends JpaRepository<SubjectStudent,Long> {
}
