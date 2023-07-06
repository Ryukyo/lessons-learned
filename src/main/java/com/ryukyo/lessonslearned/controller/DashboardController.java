package com.ryukyo.lessonslearned.controller;

import com.ryukyo.lessonslearned.model.Person;
import com.ryukyo.lessonslearned.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @Value("${lessonslearned.pageSize}")
    private int defaultPageSize;

    @Value("${lessonslearned.contact.successMsg}")
    private String message;

    @Autowired
    Environment environment;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication,
        HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if (null != person.getSchoolClass() && null != person.getSchoolClass().getName()) {
            model.addAttribute("enrolledClass", person.getSchoolClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }

    // testing
    public void logMessages() {
        log.error("defaultPageSize value with @Value annotation is : " + defaultPageSize);
        log.error("successMsg value with @Value annotation is : " + message);

        log.error("defaultPageSize value with Environment is : " + environment.getProperty(
            "eazyschool.pageSize"));
        log.error("successMsg value with Environment is : " + environment.getProperty(
            "eazyschool.contact.successMsg"));
        log.error(
            "Java Home environment variable using Environment is : " + environment.getProperty(
                "JAVA_HOME"));
    }

}
