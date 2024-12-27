package com.tat1roliv.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//@Data // lombok para gerar getters, setters e toString
@Entity //para representar uma entidade no BD
public class Lesson {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Column(length = 100, nullable = false)
    private String youtubeUrl;

   @ManyToOne(fetch = FetchType.LAZY, optional = false) //muitas aulas para um curso
   @JoinColumn(name = "course_id", nullable = false)
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // evitar dependencia ciclica
   private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Lesson [id=").append(id).append(", name=").append(name).append(", youtubeUrl=")
                .append(youtubeUrl).append(", course=").append(course).append("]");
        return builder.toString();
    }

    
   


}
