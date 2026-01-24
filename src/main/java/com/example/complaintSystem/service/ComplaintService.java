package com.example.complaintSystem.service;

import com.example.complaintSystem.dto.ComplaintCreateDto;
import com.example.complaintSystem.dto.ComplaintResponseDto;
import org.springframework.data.domain.Page;

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


}
