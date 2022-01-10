package com.example.computershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_subject")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class SubjectStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long studentId;

    public SubjectStudent(Long subjectId, Long studentId) {
        this.subjectId = subjectId;
        this.studentId = studentId;
    }
}
