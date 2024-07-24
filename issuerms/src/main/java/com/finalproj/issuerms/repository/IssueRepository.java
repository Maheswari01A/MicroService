package com.finalproj.issuerms.repository;

import com.finalproj.issuerms.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}