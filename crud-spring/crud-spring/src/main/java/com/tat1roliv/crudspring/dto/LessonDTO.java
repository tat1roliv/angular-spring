package com.tat1roliv.crudspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonDTO(    
Long id,
    @NotNull @NotBlank String name,
    @NotNull @NotBlank String youtubeUrl) {

}
