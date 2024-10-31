package com.tat1roliv.crudspring.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tat1roliv.crudspring.dto.CourseDTO;
import com.tat1roliv.crudspring.model.Course;
import com.tat1roliv.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping("/api/courses")

public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    public String requestMethodName(@RequestParam String param) {
        return new String();
    }

    //get service
    @GetMapping
    public @ResponseBody List<CourseDTO> list() {
        return courseService.list();
    }
    
    //findbyid service
    @GetMapping("/{id}")
    public Course findById(@PathVariable("id") @NotNull @Positive Long id) {
       return courseService.findById(id);
    }

    //create service
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course){
        return courseService.create(course);  
    }

    //update service
    @PutMapping("/{id}")
    public Course update(@PathVariable("id") @NotNull @Positive Long id, @RequestBody @Valid Course course){
        return courseService.update(id, course);
    }

    //delete service
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @NotNull @Positive Long id){
        courseService.delete(id);  
    }
    
}