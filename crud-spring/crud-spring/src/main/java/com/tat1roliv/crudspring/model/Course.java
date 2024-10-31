package com.tat1roliv.crudspring.model;

import java.lang.annotation.ElementType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tat1roliv.crudspring.enums.Category;
import com.tat1roliv.crudspring.enums.converters.CategoryConvertor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // = getters + setters + tostring
@Entity
//@Table(name = "courses") no caso de nao ser o mesmo nome da entidade, ej bd legado
@SQLDelete(sql = "UPDATE Course SET status = 'Inactive' WHERE id=?")
@SQLRestriction("status <> 'Inactive'")

public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;


    @NotBlank
    @NotNull
    //@Length(min = 5, max = 100)
    @Size(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    //@Length(max = 10)
    //@Size(max = 10)
    //@Pattern(regexp = "Back-end|Front-end")
    //@Enumerated(EnumType.STRING)
    @Convert(converter = CategoryConvertor.class)
    @Column(length = 10, nullable = false)
    private Category category;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "Active|Inactive")
    @Column(length = 10, nullable = false)
    private String status = "Active";

    
}
