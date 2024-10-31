package com.tat1roliv.crudspring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tat1roliv.crudspring.dto.CourseDTO;
import com.tat1roliv.crudspring.exception.RecordNotFoundException;
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
        public List<CourseDTO> list() {
                List<Course> courses = courseRepository.findAll();
                List<CourseDTO> dtos = new ArrayList<>();
                for(Course course : courses){
                    //System.out.println(course);
                    CourseDTO dto = new CourseDTO(course.getId(), course.getName(), course.getCategory());
                    dtos.add(dto);
                }       
                return dtos;
        }

        //edit DB
        public Course findById(@PathVariable("id") @NotNull @Positive Long id) {
                return courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
        }

        //create DB
        public Course create(@RequestBody @Valid Course course){
                return courseRepository.save(course);  
        }

        //update DB
        public Course update(@PathVariable("id") @NotNull @Positive Long id, @Valid Course course){
                return courseRepository.findById(id)
                .map(recordFound -> 
                {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
        }

        //delete DB - soft delete
        public void delete(@PathVariable("id") @NotNull @Positive Long id){
                courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
        }

}
