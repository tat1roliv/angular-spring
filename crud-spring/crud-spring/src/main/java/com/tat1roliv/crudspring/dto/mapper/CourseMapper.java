package com.tat1roliv.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.tat1roliv.crudspring.dto.CourseDTO;
import com.tat1roliv.crudspring.enums.Category;
import com.tat1roliv.crudspring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null; //avoid null pointer exception
        }
        //return new CourseDTO(course.getId(), course.getName(), course.getCategory());
        return new CourseDTO(course.getId(), course.getName(), "Front-end");
    }

    public Course toEntity(CourseDTO courseDTO) {

        if (courseDTO == null) {
            return null; //avoid null pointer exception
        }

        Course course = new Course();

        if(courseDTO.id() != null) {

            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(Category.FRONT_END);
        course.setStatus("Active");
        return course;
    }
    
}
