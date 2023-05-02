package com.banigeo.webpoc.controller;

import com.banigeo.webpoc.dto.department.DepartmentRequest;
import com.banigeo.webpoc.dto.department.location.LocationRequest;
import com.banigeo.webpoc.dto.department.region.RegionRequest;
import com.banigeo.webpoc.service.LocationService;
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
@RequestMapping("/location")
@Validated
@AllArgsConstructor
public class LocationController {
    private LocationService locationService;

    @GetMapping("/list")
    public ModelAndView locationList() {
        ModelAndView mv = new ModelAndView("locationList");
        mv.addAllObjects(Map.of("locations", locationService.getLocations()));
        return mv;
    }

    @RequestMapping("/register")
    public String registrationForm(Model model) {
        model.addAllAttributes(Map.of("location", new LocationRequest(),
                "locations", locationService.getLocations()));
        return "locationRegister";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute LocationRequest request, BindingResult result) {
        if(result.hasErrors()){
            return "locationRegister";
        }
        locationService.createLocation(request);
        return "redirect:/location/list";
    }
}
