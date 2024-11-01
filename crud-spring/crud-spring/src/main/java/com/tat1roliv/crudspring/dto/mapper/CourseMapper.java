package com.tat1roliv.crudspring.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tat1roliv.crudspring.dto.CourseDTO;
import com.tat1roliv.crudspring.dto.LessonDTO;
import com.tat1roliv.crudspring.enums.Category;
import com.tat1roliv.crudspring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null; //avoid null pointer exception
        }
        List<LessonDTO> lessons = course.getLessons()
        .stream()
        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
        .collect(Collectors.toList());
        
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(),
        lessons);
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
        course.setCategory(convertCategotyValue(courseDTO.category()));
        return course;
    }

    public Category convertCategotyValue(String value) {
        if(value == null) {
            return null;
        }

        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Invalid value: " + value);
        };

    }
    
}
