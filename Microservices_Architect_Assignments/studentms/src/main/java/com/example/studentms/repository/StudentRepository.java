package com.example.studentms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentms.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
