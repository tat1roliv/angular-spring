package com.tat1roliv.crudspring.controller;

import org.springframework.web.bind.annotation.RestController;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tat1roliv.crudspring.model.Course;
import com.tat1roliv.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor

public class CourseController {

    private final CourseRepository courseRepository;

    
    public String requestMethodName(@RequestParam String param) {
        return new String();
    }
    
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
 
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public void create(){

    }

}