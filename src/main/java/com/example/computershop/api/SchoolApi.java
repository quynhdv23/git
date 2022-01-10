package com.example.computershop.api;

import com.example.computershop.dto.response.SchoolDTO;
import com.example.computershop.entities.ResponseObject;
import com.example.computershop.entities.School;
import com.example.computershop.exception.ResourceNotFoundException;
import com.example.computershop.service.SchoolService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/school")
public class SchoolApi {

    private final SchoolService schoolService;


    public SchoolApi(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/all")
    public Page<School> getAllSchoolPaging(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "6") int size) {
        return schoolService.getAllSchoolForPagable(page - 1, size);
    }

    @GetMapping
    public ResponseEntity<?> getSchools() {
        List<SchoolDTO> schools = schoolService.getListSchool();
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        School school = schoolService.getSchoolById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found for this id :: " + id));
        return ResponseEntity.ok().body(school);
    }
    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    //@PostMapping
    public ResponseEntity<?> addSchool(@RequestBody @Valid School school, BindingResult result) {
        ResponseObject ro = new ResponseObject();
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                if (errors.put(fieldError.getField(), fieldError.getDefaultMessage()) != null) {
                    throw new IllegalStateException("Duplicate key");
                }
            }
            ro.setErrorMessages(errors);
            ro.setStatus("fail");

            return ResponseEntity.badRequest().body(ro);
        } else {
            schoolService.update(school);
            ro.setData(school);
            ro.setStatus("success");
            return ResponseEntity.ok().body(ro);
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEmployee(@Positive @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        schoolService.getSchoolById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found for this id :: " + id));
        schoolService.deletedById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping
    public ResponseEntity<?> updateDanhMuc(@RequestBody @Valid School school, BindingResult result) throws ResourceNotFoundException {
        Optional<School> schoolById = schoolService.getSchoolById(school.getSchoolId());
        if (schoolById.isPresent()) {
            ResponseObject ro = new ResponseObject();
            if (result.hasErrors()) {
                Map<String, String> errors = result.getFieldErrors().stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
                ro.setErrorMessages(errors);
                ro.setStatus("update fail");
                return ResponseEntity.badRequest().body(ro);
            } else {
                schoolService.update(school);
                ro.setData(school);
                ro.setStatus("update success");
                return ResponseEntity.ok().body(ro);
            }
        } else {
            throw new ResourceNotFoundException("School not found for this id :: " + school.getSchoolId());
        }
    }

    @GetMapping("/paging")
    public ResponseEntity<List<SchoolDTO>> getAllSchool(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "schoolId") String sortBy)
    {
        List<SchoolDTO> list =schoolService.getAllSchoolPagingAndSort(pageNo,pageSize,sortBy);
        return ResponseEntity.ok().body(list);
    }
}
