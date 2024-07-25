package com.example.feesms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fees_data")
@Getter
@Setter
public class Fees {

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

