package com.example.computershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private Long studentId;

    @Column(name = "fullName")
    private String fullName;

    @NotBlank(message = "gender is not blank")
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "birthday is not blank")
    @Column(name = "birthday")
    private String birthday;

    @Column(name = "address")
    private String address;


    @Column(name = "phone")
    private String phone;

    @Email(message = "wrong format email")
    @Column(name = "email")
    private String email;

    @NotNull(message = "status not null")
    private boolean status;

   // @Column(name = "createdAt")
    @Column(name = "createdAt",updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "deletedAt")
    private Date deletedAt;


    @ManyToOne
    @JoinColumn(name = "schoolId",referencedColumnName="schoolId")
    private School school;

    @ManyToMany(mappedBy = "students",cascade = CascadeType.ALL)
    private List<Subject> subjects=new ArrayList<>();

    public Student toStudent(){
        Student stu=new Student();
        stu.setFullName(getFullName());
        stu.setGender(getGender());
        stu.setBirthday(getBirthday());
        stu.setPhone(getPhone());
        stu.setAddress(getAddress());
        stu.setEmail(getEmail());
        stu.setStatus(isStatus());
        stu.setCreatedAt(getCreatedAt());
        stu.setUpdatedAt(getUpdatedAt());
        stu.setDeletedAt(getDeletedAt());
        stu.setSchool(getSchool());
        return stu;
    }
    public List<Subject> toSubject(){
       List<Subject> subjects= (List<Subject>) getSubjects();
        return subjects  ;
    }
}
