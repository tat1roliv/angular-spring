package com.tat1roliv.crudspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tat1roliv.crudspring.enums.Category;
import com.tat1roliv.crudspring.enums.validation.ValueOfEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record CourseDTO(
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Size(min = 5, max = 100) String name, 
    //@NotNull @Size(max = 10) @Pattern(regexp = "Back-end|Front-end") String category, 
    @NotNull @Size(max = 10) @ValueOfEnum(enumClass = Category.class) String category, 
    @NotEmpty @NotNull @Valid List<LessonDTO> lessons
    //no expor
    //@NotNull @Size(max = 10) @Pattern(regexp = "Active|Inactive") String status
) {

}
