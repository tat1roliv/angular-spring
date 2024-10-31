package com.tat1roliv.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.tat1roliv.crudspring.dto.CourseDTO;
import com.tat1roliv.crudspring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    // public Course toEntity(CourseDTO courseDTO) {
    //     return new Course(courseDTO.getId(), courseDTO.getName(), courseDTO.getCategory());
    // }
    
}
