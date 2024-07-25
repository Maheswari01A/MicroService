package com.example.studentms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentms.entity.Student;
import com.example.studentms.repository.StudentRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class StudentmsController {

    @Autowired
    StudentRepository studRepo;

    @GetMapping("/students") // An api to fetch all students
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studRepo.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/student/{id}") //An api to fetch a single student
    public ResponseEntity<Student> getStudent(@PathVariable Integer id)    
    {
        Optional<Student> st = studRepo.findById(id);       
        return st.map(student -> new ResponseEntity<>(student, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/student") //An api to create a new student
    public ResponseEntity<Student> addStudent(@RequestBody Student students) {
        Student savedStudent = studRepo.save(students);
        return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}") //An api to delete a new student
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {
        Optional<Student> st = studRepo.findById(id);
        if (st.isPresent()) {
            studRepo.delete(st.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/student") //An api to update a new student
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Optional<Student> st = studRepo.findById(student.getId());
        if (st.isPresent()) {
            Student stud = st.get();
            stud.setName(student.getName());
            stud.setCourse(student.getCourse());
            stud.setBatch_year(student.getBatch_year());
            studRepo.save(stud);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
