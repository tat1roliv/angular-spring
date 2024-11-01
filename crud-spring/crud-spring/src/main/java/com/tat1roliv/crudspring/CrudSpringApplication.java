package com.tat1roliv.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tat1roliv.crudspring.enums.Category;
import com.tat1roliv.crudspring.model.Course;
import com.tat1roliv.crudspring.model.Lesson;
import com.tat1roliv.crudspring.repository.CourseRepository;


@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular + Spring");
			c.setCategory(Category.FRONT_END);

			Lesson l = new Lesson();
			l.setName("Batukizer - Le Mellotron");
			l.setYoutubeUrl("https://www.youtube.com/watch?v=AVZ47cnppQw");
			l.setCourse(c);
			c.getLessons().add(l);
			
			courseRepository.save(c);
		};
	}

}
