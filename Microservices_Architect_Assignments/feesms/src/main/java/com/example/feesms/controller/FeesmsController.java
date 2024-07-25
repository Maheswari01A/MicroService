package com.example.feesms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feesms.FeesRepo.FeesRepository;
import com.example.feesms.dto.FeesDto;
import com.example.feesms.entity.Fees;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/fees")
public class FeesmsController {

    @Autowired
    public FeesRepository feesrepo;

    // @GetMapping("/hello")
    // public String getMethodName() {
    //     return "Hello from feesms";
    // }
    
    //An API to fetch all fees paid by a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Fees>> getStudentFees(@PathVariable("studentId") Integer studentId){
        List<Fees> studentFees = feesrepo.findByStudentId(studentId);
        return new ResponseEntity<>(studentFees, HttpStatus.OK);
    }

    //An API to pay fees for a student
    @PostMapping("/student/payfees/{studentId}")
    public ResponseEntity<Fees> payStudentFees(@PathVariable("studentId") Integer studentId, @RequestBody FeesDto feesDto) {
        Fees newFees = new Fees();
        newFees.setAmount(feesDto.getAmount());
        newFees.setStudentId(studentId);
        newFees.setPaymentDate(feesDto.getPaymentDate());
        newFees.setPaymentType(feesDto.getPaymentType());
        Fees savedfees = feesrepo.save(newFees);
        return new ResponseEntity<>(savedfees, HttpStatus.OK);
    }
}
