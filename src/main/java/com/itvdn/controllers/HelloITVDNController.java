package com.itvdn.controllers;

import com.itvdn.model.Authorization;
import com.itvdn.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloITVDNController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "/authorize-me")
    public ModelAndView authorize(ModelAndView modelAndView) {
        Authorization authorization = applicationContext.getBean("userAuthorized", Authorization.class);
        authorization.setAuthorized(Boolean.TRUE);
        modelAndView.setViewName("authorized");
        modelAndView.addObject("authorized", authorization);
        return modelAndView;
    }

    @GetMapping(value = "/unauthorize-me")
    public ModelAndView unAuthorize(ModelAndView modelAndView) {
        Authorization authorization = applicationContext.getBean("userAuthorized", Authorization.class);
        authorization.setAuthorized(Boolean.FALSE);
        modelAndView.setViewName("index");
        modelAndView.addObject("authorized", authorization);
        return modelAndView;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloITVDN() {
        return "index";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String listAllEmployee() {
        return "index";
    }


    @GetMapping(value = "/bye2")
    public ModelAndView bye(ModelAndView modelAndView) {
        modelAndView.setViewName("bye");
        return modelAndView;
    }

    @GetMapping(value = "/bye")
    public String bye() {
        return "bye";
    }

    @GetMapping(value = "/student")
    public ModelAndView student() {
        return new ModelAndView("student", "command", new Student());
    }
    @PostMapping(value = "/addStudent")
    public String addStudent(@ModelAttribute("SpringWeb")Student student, ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());

        return "result";
    }

    @GetMapping(value = "/path/{studentName}")
    public String getNameStudent(@PathVariable String studentName, Model model){
        model.addAttribute("studentName", studentName);
        return "student-name";
    }

    @GetMapping(value = "/rest/{name}")
    @ResponseBody
    public String retRest(@PathVariable String name) {
        return name + Math.random() * 1000;
    }

}
