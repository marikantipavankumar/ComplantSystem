package com.example.complaintSystem.controller;

import com.example.complaintSystem.dto.ComplaintCreateDto;
import com.example.complaintSystem.dto.ComplaintResponseDto;
import com.example.complaintSystem.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // ✅ CREATE complaint
    @PostMapping
    public ResponseEntity<ComplaintResponseDto> createComplaint(
            @Valid @RequestBody ComplaintCreateDto dto) {

        ComplaintResponseDto response = complaintService.createComplaint(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ✅ GET all complaints
    @GetMapping
    public Page<ComplaintResponseDto> getAllComplaints(
            @RequestParam(defaultValue ="0") int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "createdAt")String sortBy
    ) {
        return complaintService.getAllComplaints(page,size,sortBy);
    }

    // ✅ GET complaint by ID
    @GetMapping("/{id}")
    public ComplaintResponseDto getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    // ✅ UPDATE complaint status
    @PutMapping("/{complaintId}/status")
    public ComplaintResponseDto updateComplaintStatus(
            @PathVariable Long complaintId,
            @RequestParam String status,
            @RequestParam Long adminUserId
    ) {
        return complaintService.updateComplaintStatus(complaintId, status, adminUserId);
    }
}
