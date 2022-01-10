package com.example.computershop.dto.request;

import com.example.computershop.entities.Organization;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportantOrganizationForm {

    private Long organizationId;

    private String orgCode;

    private String orgName;

    private String orgGroup;

    public static ImportantOrganizationForm toImportantForm(UpdateOrganizationForm form){
        ImportantOrganizationForm importantForm= new ImportantOrganizationForm();
        importantForm.setOrganizationId(form.getOrganizationId());
        importantForm.setOrgCode(form.getOrgCode());
        importantForm.setOrgGroup(form.getOrgGroup());
        importantForm.setOrgName(form.getOrgName());
        return importantForm;
    }
    public static ImportantOrganizationForm toImportantForm(Organization form){
        ImportantOrganizationForm importantForm= new ImportantOrganizationForm();
        importantForm.setOrganizationId(form.getOrganizationId());
        importantForm.setOrgCode(form.getOrgCode());
        importantForm.setOrgGroup(form.getOrgGroup());
        importantForm.setOrgName(form.getOrgName());
        return importantForm;
    }
//    public static boolean checkImportant(UpdateOrganizationForm updateOrganizationForm, Organization organization){
//        ObjectMapper mapper=new ObjectMapper();
//        ImportantOrganizationForm importantOrganizationForm = ImportantOrganizationForm.toImportantForm(updateOrganizationForm);
//
//
//        Map formDatabase = mapper.convertValue(organization, Map.class);
//    }
}
