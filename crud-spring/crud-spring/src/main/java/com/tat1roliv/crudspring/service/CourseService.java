package com.tat1roliv.crudspring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.tat1roliv.crudspring.dto.CourseDTO;
import com.tat1roliv.crudspring.dto.CoursePageDTO;
import com.tat1roliv.crudspring.dto.mapper.CourseMapper;
import com.tat1roliv.crudspring.exception.RecordNotFoundException;
import com.tat1roliv.crudspring.model.Course;
import com.tat1roliv.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


@Validated
@Service
public class CourseService {
        private final CourseRepository courseRepository;
        private final CourseMapper courseMapper;

        public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
            this.courseRepository = courseRepository;
            this.courseMapper = courseMapper;
        }
        
        //get all courses DB
        //NO DELETE
        // public List<CourseDTO> list() {
        //         return courseRepository.findAll()
        //         .stream()
        //         .map(course -> courseMapper.toDTO(course))
        //         .collect(Collectors.toList());
        // }   
        
        //get all courses DB + pagination
        public CoursePageDTO findAll(@PositiveOrZero int page, @Positive @Max(1000) int pageSize) {
                Page<Course> coursePage = courseRepository.findAll(PageRequest.of(page, pageSize));
                List<CourseDTO> list = coursePage.getContent().stream()
                        .map(courseMapper::toDTO)
                        .toList();
                return new CoursePageDTO(list, coursePage.getTotalElements(), coursePage.getTotalPages());
        }  


        //find by id DB
        public CourseDTO findById(@NotNull @Positive Long id) {
                return courseRepository.findById(id)
                .map(course -> courseMapper.toDTO(course))
                .orElseThrow(() -> new RecordNotFoundException(id));
        }

        //create DB
        public CourseDTO create(@RequestBody @NotNull @Valid CourseDTO courseDTO){
                return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));  
        }

        //update DB
        public CourseDTO update(@NotNull @Positive Long id, @Valid CourseDTO courseDTO){
                return courseRepository.findById(id)
                .map(recordFound -> 
                {
                    Course course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseMapper.convertCategotyValue(courseDTO.category()));

                    recordFound.getLessons().clear();
                    course.getLessons().forEach(lesson -> {
                        //lesson.setCourse(recordFound);
                        recordFound.getLessons().add(lesson);
                    });

                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
        }

        //delete DB - soft delete
        public void delete(@NotNull @Positive Long id){
                courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
        }

}
