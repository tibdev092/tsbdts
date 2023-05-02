package com.banigeo.webpoc.controller;

import com.banigeo.webpoc.dto.department.DepartmentRequest;
import com.banigeo.webpoc.service.DepartmentService;
import com.banigeo.webpoc.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/department")
@Validated
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;
    private LocationService locationService;

    @GetMapping("/list")
    public ModelAndView departmentList() {
        ModelAndView mv = new ModelAndView("departmentList");
        mv.addAllObjects(Map.of("departments", departmentService.getDepartments()));
        return mv;
    }

    @RequestMapping("/register")
    public String registrationForm(Model model) {
        model.addAllAttributes(Map.of("department", new DepartmentRequest(),
                "locations", locationService.getLocations()));
        return "departmentRegister";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute DepartmentRequest request, BindingResult result) {
        if(result.hasErrors()){
            return "departmentRegister";
        }
        departmentService.createDepartment(request);
        return "redirect:/department/list";
    }
}
