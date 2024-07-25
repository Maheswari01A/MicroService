package com.example.feesms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeesDto {

    private double amount;
    private String paymentDate;
    private String paymentType;
    
    // Optionally, you can include a constructor for convenience
    public FeesDto(double amount, String paymentDate, String paymentType) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
    }

    // Default constructor
    public FeesDto() {}
}
