package com.tat1roliv.crudspring.controller;

import org.springframework.web.bind.annotation.RestController;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tat1roliv.crudspring.model.Course;
import com.tat1roliv.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor

public class CourseController {

    private final CourseRepository courseRepository;

    public String requestMethodName(@RequestParam String param) {
        return new String();
    }

    //read the courses in DB
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }
    
    //edit
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable("id") Long id) {
       return courseRepository.findById(id).map(recordFound -> ResponseEntity.ok().body(recordFound)).orElse(ResponseEntity.notFound().build());
    }

    //create courses in DB
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course){
        //System.out.println(course.getName());
        return courseRepository.save(course);  
    }

    /*@PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }*/

    
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable("id") Long id, @RequestBody Course course){
        return courseRepository.findById(id).map(recordFound -> 
        {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());
            Course updated = courseRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        }
        )
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        //courseRepository.deleteById(id);
        return courseRepository.findById(id).map(recordFound -> 
        {
            courseRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }
        )
        .orElse(ResponseEntity.notFound().build());
    }
    
}