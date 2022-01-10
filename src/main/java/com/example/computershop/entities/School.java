package com.example.computershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schools")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class School implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="my_seq_gen")
//    @SequenceGenerator(name="my_seq_gen", sequenceName="ENTITY_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schoolId")
    private Long schoolId;

    @NotBlank(message = "name not blank")
    @Column(name = "schoolName")
    private String schoolName;

    @NotBlank(message = "address not blank")
    @Column(name = "")
    private String schoolAddress;

    @Column(name = "contactPhone")
    private String contactPhone;

    @Email
    @Column(name = "contactEmail")
    private String contactEmail;
    @JsonIgnore
    @OneToMany(mappedBy = "school",cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @OrderBy("fullName")
    private List<Student> students;
}

