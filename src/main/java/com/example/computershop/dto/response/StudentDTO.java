package com.example.computershop.dto.response;
import com.example.computershop.entities.School;
import com.example.computershop.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long studentId;
    private String fullName;
    private String gender;
    private String birthday;
    private String address;
    private String phone;
    private String email;
    private boolean status;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
