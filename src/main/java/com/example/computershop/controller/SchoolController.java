package com.example.computershop.controller;

import com.example.computershop.dto.response.SchoolDTO;
import com.example.computershop.entities.School;
import com.example.computershop.service.SchoolService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/schools/")
public class SchoolController {
    private final SchoolService schoolService;
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
    @RequestMapping(value = {"list"})
    public String viewListSchool(Model model) {
        List<SchoolDTO> schools = schoolService.getListSchool();
        model.addAttribute("schools",schools);
        return "home";
    }
    @GetMapping("initInsert")
    public String showSignUpForm(Model model) {
        School s=new School();
        model.addAttribute("s",s);
        return "add-school";
    }
    @PostMapping("add")
    public String addStudent(@Valid School a,BindingResult result,Model model) {
        if (result.hasErrors()) {
            return "add-school";
        }
        schoolService.save(a);
        return "redirect:list";
    }
    @GetMapping("edit/{id}")
    public String editSchool(@PathVariable("id") long id, Model model) {
        Optional<School> schoolEdit = schoolService.getSchoolById(id);
        schoolEdit.ifPresent(school -> model.addAttribute("school", school));
        return "edit-school";
    }

    @PostMapping(value = "update")
    public String saveUpdate(@Valid School school,Model model,
                             BindingResult result, RedirectAttributes reModel) {

        if (result.hasErrors()) {
            model.addAttribute("failed","update Failed");
            return "edit-school";
        }
        schoolService.save(school);
        reModel.addFlashAttribute("success","Update success");
        return "redirect:list";
    }
    @GetMapping("delete/{id}")
    public String deleteSchool(@PathVariable("id") long schoolId,RedirectAttributes reModel) {
       schoolService.deletedById(schoolId);
        reModel.addFlashAttribute("success","delete successfully");
        return "redirect:/admin/schools/list";
    }
}
