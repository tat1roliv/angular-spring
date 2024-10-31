package com.tat1roliv.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.tat1roliv.crudspring.dto.CourseDTO;
import com.tat1roliv.crudspring.dto.mapper.CourseMapper;
import com.tat1roliv.crudspring.exception.RecordNotFoundException;
import com.tat1roliv.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


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
        public List<CourseDTO> list() {
                return courseRepository.findAll()
                .stream()
                .map(course -> courseMapper.toDTO(course))
                .collect(Collectors.toList());
        }       


        //find by id DB
        public CourseDTO findById(@NotNull @Positive Long id) {
                return courseRepository.findById(id)
                .map(course -> courseMapper.toDTO(course))
                .orElseThrow(() -> new RecordNotFoundException(id));
        }

        //create DB
        public CourseDTO create(@RequestBody @NotNull @Valid CourseDTO course){
                return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));  
        }

        //update DB
        public CourseDTO update(@NotNull @Positive Long id, @Valid CourseDTO course){
                return courseRepository.findById(id)
                .map(recordFound -> 
                {
                    recordFound.setName(course.name());
                    recordFound.setCategory(courseMapper.convertCategotyValue(course.category()));
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
        }

        //delete DB - soft delete
        public void delete(@NotNull @Positive Long id){
                courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
        }

}
