package com.example.book_project.service.impl;

import com.example.book_project.dto.request.BookRequestDTO;
import com.example.book_project.dto.request.MemberRequestDTO;
import com.example.book_project.dto.response.BookResponseDTO;
import com.example.book_project.dto.response.MemberResponseDTO;
import com.example.book_project.model.Book;
import com.example.book_project.model.Member;
import com.example.book_project.repository.MemberRepository;
import com.example.book_project.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberResponseDTO createMember(MemberRequestDTO memberRequestDTO){
        Member member = new Member();
        member.setName(memberRequestDTO.getName());
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDTO(savedMember.getId(), savedMember.getName());
    }

    @Override
    public List<MemberResponseDTO> getAllMembers(){
        return memberRepository.findAll()
                .stream()
                .map(member -> new MemberResponseDTO(member.getId(), member.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponseDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return new MemberResponseDTO(member.getId(), member.getName());
    }

    @Override
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}
