package com.example.complaintSystem.service;

import com.example.complaintSystem.dto.ComplaintCreateDto;
import com.example.complaintSystem.dto.ComplaintResponseDto;
import com.example.complaintSystem.entity.Complaint;
import com.example.complaintSystem.entity.User;
import com.example.complaintSystem.exception.BadRequestException;
import com.example.complaintSystem.exception.ResourceNotFoundException;
import com.example.complaintSystem.mapper.ComplaintMapper;
import com.example.complaintSystem.repository.ComplaintRepository;
import com.example.complaintSystem.repository.UserRepository;
import com.example.complaintSystem.service.ComplaintService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public ComplaintServiceImpl(
            ComplaintRepository complaintRepository,
            UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ComplaintResponseDto createComplaint(ComplaintCreateDto dto) {

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new BadRequestException("Title is required");
        }
        if (dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new BadRequestException("Description is required");
        }
        if (dto.getUserId() == null) {
            throw new BadRequestException("User ID is required");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Complaint complaint = ComplaintMapper.toEntity(dto, user);
        return ComplaintMapper.toResponseDto(
                complaintRepository.save(complaint)
        );
    }

    @Override
    public Page<ComplaintResponseDto> getAllComplaints(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).descending());

        Page<Complaint> complaintPage = complaintRepository.findAll(pageable);
        return complaintPage.map(ComplaintMapper::toResponseDto);

    }


    @Override
    public ComplaintResponseDto getComplaintById(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
        return ComplaintMapper.toResponseDto(complaint);
    }

    @Override
    public ComplaintResponseDto updateComplaintStatus(
            Long complaintId,
            String status,
            Long adminUserId) {

        if (status == null || status.isBlank()) {
            throw new BadRequestException("Status cannot be empty");
        }

        User adminUser = userRepository.findById(adminUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!"ADMIN".equalsIgnoreCase(adminUser.getRole())) {
            throw new BadRequestException("Only ADMIN can update complaint status");
        }

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        complaint.setStatus(status.toUpperCase());
        return ComplaintMapper.toResponseDto(
                complaintRepository.save(complaint)
        );
    }


}
