package com.tat1roliv.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Size(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(length = 10, nullable = false)
    private String category;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "Active|Inactive")
    @Column(length = 10, nullable = false)
    private String status = "Active";

    
}
