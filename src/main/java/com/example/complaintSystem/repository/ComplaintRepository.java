package com.example.complaintSystem.repository;

import com.example.complaintSystem.entity.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    Page<Complaint> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Complaint> findByStatus(String status, Pageable pageable);



}
