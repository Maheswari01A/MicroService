
package com.example.studentms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students_data")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue()
    Integer id;
    private String name;
    private String course;
    private Integer batch_year;
}