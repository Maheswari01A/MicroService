package com.example.studentms.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class FeesDto {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment_type")
    private String paymentType;
}

