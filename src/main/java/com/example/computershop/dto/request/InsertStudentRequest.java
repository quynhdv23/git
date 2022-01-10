package com.example.computershop.dto.request;

import com.example.computershop.entities.School;
import com.example.computershop.entities.Student;
import com.example.computershop.entities.Subject;
import lombok.*;

import java.util.List;
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InsertStudentRequest {
    private Student studentRequest;
    
   
}
