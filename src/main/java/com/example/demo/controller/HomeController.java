package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class HomeController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("list", studentService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createStudent(@ModelAttribute Student student) {
        MultipartFile file = student.getImage();
        String fileName = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Student student1 = new Student(student.getName(),fileName);
        student.setAvatar(fileName);
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", student);
        return modelAndView;
    }
}
