package com.example.computershop.dto.response;
import com.example.computershop.entities.School;
import com.example.computershop.entities.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class SchoolDTO {
    private Long schoolId;

    private String schoolName;

    private String schoolAddress;

    private String contactPhone;

    private String contactEmail;

    private List<Student> listStudent;

}
