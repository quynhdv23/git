package com.example.computershop.service;

import com.example.computershop.dto.request.UpdateOrganizationForm;
import com.example.computershop.entities.Organization;

import java.util.Optional;

public interface OrganizationService {
    Optional<Organization> findById(Long orgId);

    void update (UpdateOrganizationForm form);
}
