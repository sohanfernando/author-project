package com.example.book_project.controller;

import com.example.book_project.dto.request.MemberRequestDTO;
import com.example.book_project.dto.response.MemberResponseDTO;
import com.example.book_project.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<MemberResponseDTO> createMember(@RequestBody MemberRequestDTO memberRequestDTO){
        return ResponseEntity.ok(memberService.createMember(memberRequestDTO));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers(){
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable Long id){
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable Long id){
        memberService.deleteMemberById(id);
        return ResponseEntity.noContent().build();
    }
}
