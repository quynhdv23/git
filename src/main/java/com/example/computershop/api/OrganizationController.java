package com.example.computershop.api;

import com.example.computershop.dto.request.UpdateOrganizationForm;
import com.example.computershop.entities.Organization;
import com.example.computershop.entities.ResponseObject;
import com.example.computershop.entities.School;
import com.example.computershop.exception.ResourceNotFoundException;
import com.example.computershop.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Organization organization = organizationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found for this id :: " + id));
        return ResponseEntity.ok().body(organization);
    }

    @PutMapping
    public ResponseEntity<?> updateOrganization(@RequestBody UpdateOrganizationForm form) {
        organizationService.update(form);
        return ResponseEntity.ok().build();
    }
}
