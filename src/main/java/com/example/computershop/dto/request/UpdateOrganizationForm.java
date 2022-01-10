package com.example.computershop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrganizationForm {

    private Long organizationId;

    private String orgCode;

    private String orgName;

    private String orgGroup;

    private Long org_level;

    private Long status;

    private String address;

}
