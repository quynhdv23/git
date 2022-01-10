package com.example.computershop.repository;

import com.example.computershop.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value="select * from students s  where s.schoolId= ?1",
            nativeQuery = true)
    List<Student> getListStudentBySchoolId(Long schoolId);
}
