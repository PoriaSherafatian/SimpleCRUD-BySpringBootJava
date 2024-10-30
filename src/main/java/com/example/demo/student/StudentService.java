package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudent() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("This email is already exist...");
        }
        studentRepository.save(student);
    }


    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("This student does not exists by this id : " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
    Student student = studentRepository.findById(studentId).orElseThrow( () -> new IllegalStateException(
            "This student does not exists by this id : " + studentId));

    if( name != null &&
        name.length() > 0 &&
        !Objects.equals(student.getName(), name)) {
        student.setName(name);
    }

    if( email != null &&
        email.length() > 0 &&
        !Objects.equals(student.getEmail(), email)) {
    Optional<Student> studentOptional = studentRepository
        .findStudentByEmail(email);
    if (studentOptional.isPresent()) {
        throw new IllegalStateException("This email is already exist...");
    }
    student.setEmail(email);
    }


    }
}