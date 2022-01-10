package com.example.computershop.api;

import com.example.computershop.entities.ResponseObject;
import com.example.computershop.entities.SubjectStudent;
import com.example.computershop.exception.ResourceNotFoundException;
import com.example.computershop.service.SubjectStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/ss")
public class SubjectStudentApi {



    final private SubjectStudentService subjectStudentService;

    public SubjectStudentApi(SubjectStudentService subjectStudentService) {
        this.subjectStudentService = subjectStudentService;
    }

    @GetMapping
    public ResponseEntity<?> getSchools() {
        List<SubjectStudent> ss = subjectStudentService.getListSchool();
        return ResponseEntity.ok(ss);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectStudent> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        SubjectStudent ss = subjectStudentService.getSubjectStuById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found for this id :: " + id));
        return ResponseEntity.ok().body(ss);
    }

    @PostMapping
    public ResponseEntity<?> addSchool(@RequestBody @Valid SubjectStudent subjectStudent, BindingResult result) {
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
            subjectStudentService.update(subjectStudent);
            ro.setData(subjectStudent);
            ro.setStatus("success");
            return ResponseEntity.ok().body(ro);
        }
    }
}