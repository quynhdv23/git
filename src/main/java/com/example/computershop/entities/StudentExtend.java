package com.example.computershop.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentExtend {
    private Long studentId;

    private String fullName;


    private String gender;


    private String birthday;


    private String address;


    private String phone;

    private String email;


    private boolean status;

    private Timestamp createdAt;

    private Timestamp updatedAt;


    private String deletedAt;

    private School school;

}
