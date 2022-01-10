package com.example.computershop.dto;

import com.example.computershop.entities.School;
import com.example.computershop.entities.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String schoolName;
    private String schoolAddress;
    private String contactPhone;
    private String contactEmail;
    @OneToMany(mappedBy = "school")
    private List<Student> listStudent;
}
