package com.Digital.Library.Management.System.Digital.Library.Management.System.controller;

import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Member;
import com.Digital.Library.Management.System.Digital.Library.Management.System.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody Member member) {
        try {
            Member savedMember = memberService.save(member);
            return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.findById(id);
        if (member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<?> getMemberByUsername(@PathVariable String username) {
        Optional<Member> member = memberService.findByUsername(username);
        if (member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteById(id);
        return new ResponseEntity<>("Delete operation completed", HttpStatus.OK);
    }
}
