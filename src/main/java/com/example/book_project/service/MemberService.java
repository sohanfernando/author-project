package com.example.book_project.service;

import com.example.book_project.dto.request.MemberRequestDTO;
import com.example.book_project.dto.response.MemberResponseDTO;

import java.util.List;

public interface MemberService {
    MemberResponseDTO createMember(MemberRequestDTO memberRequestDTO);
    List<MemberResponseDTO> getAllMembers();
    MemberResponseDTO getMemberById(Long id);
    void deleteMemberById(Long id);
}
