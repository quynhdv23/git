package com.example.computershop.dto.request;
import com.example.computershop.entities.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertStudent {
    private String fullName;
    @NotNull
    private String gender;
    @NotNull
    private String birthday;
    private String address;
    private String phone;
    private String email;
    private boolean status;
    private School school;
}
