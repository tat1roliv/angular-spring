package com.tat1roliv.crudspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDTO {
    
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 100)
    private String name;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    private String category;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "Active|Inactive")
    private String status = "Active";

}
