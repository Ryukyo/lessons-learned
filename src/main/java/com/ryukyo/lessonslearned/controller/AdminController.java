package com.ryukyo.lessonslearned.controller;

import com.ryukyo.lessonslearned.model.SchoolClass;
import com.ryukyo.lessonslearned.repository.PersonRepository;
import com.ryukyo.lessonslearned.repository.SchoolClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("schoolClasses",schoolClasses);
        modelAndView.addObject("schoolClass", new SchoolClass());
        return modelAndView;
    }
}
