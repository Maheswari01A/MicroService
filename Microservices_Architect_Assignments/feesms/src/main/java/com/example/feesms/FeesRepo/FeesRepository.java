package com.example.feesms.FeesRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.feesms.entity.Fees;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Integer> {

    List<Fees> findByStudentId(Integer studentId);
}
