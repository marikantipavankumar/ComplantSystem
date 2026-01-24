package com.example.complaintSystem.mapper;


import com.example.complaintSystem.dto.ComplaintCreateDto;
import com.example.complaintSystem.dto.ComplaintResponseDto;
import com.example.complaintSystem.entity.Complaint;
import com.example.complaintSystem.entity.User;

public class ComplaintMapper {

    // Request DTO → Entity
    public static Complaint toEntity(ComplaintCreateDto dto, User user) {
        Complaint complaint = new Complaint();
        complaint.setTitle(dto.getTitle());
        complaint.setDescription(dto.getDescription());
        complaint.setUser(user);
        complaint.setStatus("OPEN");
        return complaint;
    }

    // Entity → Response DTO
    public static ComplaintResponseDto toResponseDto(Complaint complaint) {
        ComplaintResponseDto dto = new ComplaintResponseDto();
        dto.setId(complaint.getId());
        dto.setTitle(complaint.getTitle());
        dto.setDescription(complaint.getDescription());
        dto.setStatus(complaint.getStatus());
        dto.setCreatedAt(complaint.getCreatedAt());

        if (complaint.getUser() != null) {
            dto.setUserId(complaint.getUser().getId());
            dto.setUserName(complaint.getUser().getName());
        }

        return dto;
    }
}

