package com.example.computershop.api;

import com.example.computershop.dto.request.StudentRequest;
import com.example.computershop.entities.ResponseObject;
import com.example.computershop.entities.School;
import com.example.computershop.entities.Student;
import com.example.computershop.entities.SubjectStudent;
import com.example.computershop.exception.ResourceNotFoundException;
import com.example.computershop.service.SchoolService;
import com.example.computershop.service.StudentService;
import com.example.computershop.service.SubjectService;
import com.example.computershop.service.SubjectStudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/student")
public class StudentApi {

    private final StudentService studentService;
    private final SchoolService schoolService;
    private final SubjectStudentService subStu;
    private final SubjectService subjectService;

    public StudentApi(StudentService studentService, SchoolService schoolService, SubjectStudentService subStu, SubjectService subjectService) {
        this.studentService = studentService;
        this.schoolService = schoolService;
        this.subStu = subStu;
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public Page<Student> getAllStudentPaging(@RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "size", defaultValue = "6") int size){
        return studentService.getAllStudentForPagable(page - 1, size);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));
        return ResponseEntity.ok().body(student);
    }
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student request, BindingResult result) throws ResourceNotFoundException{
        ResponseObject ro = new ResponseObject();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            ro.setErrorMessages(errors);
            ro.setStatus("fail");

            return ResponseEntity.badRequest().body(ro);
        } else {
            Student student = request.toStudent();
            Student studentInserted = studentService.save(student);
            School schoolById = schoolService.getSchoolById(studentInserted.getSchool().getSchoolId()).orElse(null);


            //kiem tra ids co ton tai ko
            List<Long> subIds = request.getSubjects().stream().map(x -> x.getSubjectId()).collect(Collectors.toList());
            List<Long> ids= subjectService.getListSubject().stream().map(x->x.getSubjectId()).collect(Collectors.toList());
            boolean check=ids.containsAll(subIds);
           //            request.toSubject().stream().map(x ->subStu.save(new SubjectStudent(x.getSubjectId(),studentInserted.getStudentId())));
            if(check==true){
                request.toSubject().stream().forEach(x->subStu.save(new SubjectStudent(x.getSubjectId(),studentInserted.getStudentId())));
            }else {
               throw new ResourceNotFoundException("SubjectIds không tồn tại :: " + subIds);
            }
            Student studentById = studentService.getStudentById(studentInserted.getStudentId()).orElse(null);
            studentById.setSchool(schoolById);

            ro.setData(studentById);
            ro.setStatus("success");
            return ResponseEntity.ok().body(ro);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateDanhMuc(@RequestBody @Valid StudentRequest request,BindingResult result) throws ResourceNotFoundException {
        Optional<Student> studentOptional = studentService.getStudentById(request.getStudentId());
        if (studentOptional.isPresent()) {
            ResponseObject ro = new ResponseObject();
            if (result.hasErrors()) {

                Map<String, String> errors = result.getFieldErrors().stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
                ro.setErrorMessages(errors);
                ro.setStatus("update fail");
                return ResponseEntity.badRequest().body(ro);
            } else {
                Student student=new Student();
                student.setStudentId(studentOptional.get().getStudentId());
                if(request.getFullName()!=null){
                    student.setFullName(request.getFullName());
                }
                if(request.getGender()!=null){
                    student.setGender(request.getGender());
                }
                if(request.getAddress()!=null){
                    student.setAddress(request.getAddress());
                }
                if(request.getBirthday()!=null){
                    student.setBirthday(request.getBirthday());
                }
                if(request.getPhone()!=null){
                    student.setPhone(request.getPhone());
                }
                if(request.getEmail()!=null){
                    student.setEmail(request.getEmail());
                }
                student.setStatus(request.isStatus());
                student.setSchool(studentOptional.get().getSchool());
                student.setCreatedAt(studentOptional.get().getCreatedAt());
                student.setUpdatedAt(studentOptional.get().getUpdatedAt());
                student.setDeletedAt(studentOptional.get().getDeletedAt());
                studentService.update(student);

                ro.setData(student);
                ro.setStatus("update success");
                return ResponseEntity.ok().body(ro);
            }
        } else {
            throw new ResourceNotFoundException("School not found for this id :: " + request.getStudentId());
        }
    }
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        studentService.getStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));
        studentService.deletedById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
