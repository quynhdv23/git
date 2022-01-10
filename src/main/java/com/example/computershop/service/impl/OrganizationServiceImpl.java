package com.example.computershop.service.impl;

import com.example.computershop.dto.request.ImportantOrganizationForm;
import com.example.computershop.dto.request.UpdateOrganizationForm;
import com.example.computershop.entities.Organization;
import com.example.computershop.exception.ResourceNotFoundException;
import com.example.computershop.repository.OrganizationRepository;
import com.example.computershop.service.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Optional<Organization> findById(Long orgId) {
        return organizationRepository.findById(orgId);
    }

    @Override
    public void update(UpdateOrganizationForm form) {

        ImportantOrganizationForm importantOrganizationForm = ImportantOrganizationForm.toImportantForm(form);
//        ObjectMapper mapper=new ObjectMapper();
//        Map updateForm = mapper.convertValue(importantOrganizationForm, Map.class);
        Optional<Organization> organizationById = findById(form.getOrganizationId());
        if(organizationById.isEmpty()){
            throw new ResourceNotFoundException("Không thấy đâu"+form.getOrganizationId());
        }
        var organization=organizationById.get();
        ImportantOrganizationForm importantOrganizationForm2 = ImportantOrganizationForm.toImportantForm(organization);
//        Map formDatabase = mapper.convertValue(importantOrganizationForm2, Map.class);
        boolean check = importantOrganizationForm2.equals(importantOrganizationForm);
        if(!check){
            organization.withAddress(form.getAddress())
                            .withOrg_level(form.getOrg_level())
                                    .withStatus(2L);
            organizationRepository.save(organization);
        }

    }
}
