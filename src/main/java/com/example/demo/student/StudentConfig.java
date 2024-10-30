package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {

            Student Poria = new Student(
                    "Poria",
                    "Test@gmail.com",
                    LocalDate.of(2000, Month.APRIL , 20)
            );

            Student Dorsa = new Student(
                    "Dorsa",
                    "Test2@gmail.com",
                    LocalDate.of(2000, Month.APRIL , 20)
            );

            repository.saveAll(
                    List.of(Poria, Dorsa)
            );
        };
    }
}
