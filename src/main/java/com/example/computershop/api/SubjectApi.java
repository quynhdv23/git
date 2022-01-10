package com.example.computershop.api;

import com.example.computershop.entities.ResponseObject;
import com.example.computershop.entities.School;
import com.example.computershop.entities.Subject;
import com.example.computershop.exception.ResourceNotFoundException;
import com.example.computershop.service.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/subject")
public class SubjectApi {

    private final SubjectService subjectService;

    public SubjectApi(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @GetMapping("/all")
    public Page<Subject> getAllSchoolPaging(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "6") int size) {
        return subjectService.getAllSubjectForPagable(page - 1, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Subject> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Subject subject = subjectService.getSubjectById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found for this id :: " + id));
        return ResponseEntity.ok().body(subject);
    }

    @PostMapping
    public ResponseEntity<?> addSchool(@RequestBody @Valid Subject subject, BindingResult result) {
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
            subjectService.update(subject);
            ro.setData(subject);
            ro.setStatus("success");
            return ResponseEntity.ok().body(ro);
        }
    }


}
