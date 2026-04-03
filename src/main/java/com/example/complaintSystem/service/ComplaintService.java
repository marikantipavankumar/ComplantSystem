package com.example.complaintSystem.service;

import com.example.complaintSystem.dto.ComplaintCreateDto;
import com.example.complaintSystem.dto.ComplaintResponseDto;
import com.example.complaintSystem.mapper.ComplaintMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.List;

public interface ComplaintService {

    ComplaintResponseDto createComplaint(ComplaintCreateDto dto);

    Page<ComplaintResponseDto> getAllComplaints(int page, int size, String sortBy);

    ComplaintResponseDto getComplaintById(Long id);

    ComplaintResponseDto updateComplaintStatus(
            Long complaintId,
            String status,
            Long adminUserId
    );

    public Page<ComplaintResponseDto> getComplaintsByStatus(String status, int page, int size);
}