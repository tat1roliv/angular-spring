package com.tat1roliv.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tat1roliv.crudspring.model.Course;
import com.tat1roliv.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
@Service

public class CourseService {
        private final CourseRepository courseRepository;

        public CourseService(CourseRepository courseRepository) {
            this.courseRepository = courseRepository;
        }
        
        //get all courses DB
        public List<Course> list() {
                return courseRepository.findAll();
        }

        //edit DB
        public Optional<Course> findById(@PathVariable("id") @NotNull @Positive Long id) {
                return courseRepository.findById(id);
        }

        //create DB
        public Course create(@RequestBody @Valid Course course){
                return courseRepository.save(course);  
        }

        //update DB
        public Optional<Course> update(@PathVariable("id") @NotNull @Positive Long id, @Valid Course course){
                return courseRepository.findById(id)
                .map(recordFound -> 
                {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                });
        }

        //delete DB - soft delete
        public boolean delete(@PathVariable("id") @NotNull @Positive Long id){
                return courseRepository.findById(id).map(recordFound -> 
                {
                courseRepository.deleteById(id); // hard delete handled by spring
                return true;
                }
                )
                .orElse(false);
        }

}
