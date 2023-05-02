package com.banigeo.webpoc.controller;

import com.banigeo.webpoc.dto.department.region.RegionRequest;
import com.banigeo.webpoc.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/region")
@Validated
@AllArgsConstructor
public class RegionController {
    private RegionService regionService;

    @GetMapping("/list")
    public ModelAndView regionList() {
        ModelAndView mv = new ModelAndView("regionList");
        mv.addAllObjects(Map.of("regions", regionService.getRegions()));
        return mv;
    }

    @RequestMapping("/register")
    public String registrationForm(Model model) {
        model.addAllAttributes(Map.of("region", new RegionRequest(),
                "regions", regionService.getRegions()));
        return "regionRegister";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute RegionRequest request, BindingResult result) {
        if(result.hasErrors()){
            return "regionRegister";
        }
        regionService.createRegion(request.getName());
        return "redirect:/region/list";
    }
}
