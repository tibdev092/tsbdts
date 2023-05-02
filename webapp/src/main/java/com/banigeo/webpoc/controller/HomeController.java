package com.banigeo.webpoc.controller;

import com.banigeo.webpoc.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@AllArgsConstructor
public class HomeController {

    private EmployeeService employeeService;

    @GetMapping("/showLogInForm")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError() {
        return "loginError";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }

    @RequestMapping({"","/","/index"})
    public ModelAndView employeesList() {
        ModelAndView mv = new ModelAndView("employeeList");
        mv.addAllObjects(Map.of("employees", employeeService.getSliceOfEmployees(PageRequest.of(0,10))));
        return mv;
    }
}
