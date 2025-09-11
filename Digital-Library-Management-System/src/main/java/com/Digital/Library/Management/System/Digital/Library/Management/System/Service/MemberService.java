package com.Digital.Library.Management.System.Digital.Library.Management.System.Service;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Member;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // -------------------- SAVE MEMBER --------------------
    public <S extends Member> S save(S entity) {
        if (entity == null) {
            throw new RuntimeException("Invalid member");
        }

        // Business rule: username must be unique
        Optional<Member> existing = memberRepository.findByUsername(entity.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("Username '" + entity.getUsername() + "' is already taken.");
        }

        return memberRepository.save(entity);
    }

    //  FIND MEMBER BY ID
    public Optional<Member> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Member ID cannot be null");
        }
        return memberRepository.findById(id);
    }

    // FIND MEMBER BY USERNAME
    public Optional<Member> findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        Optional<Member> member = memberRepository.findByUsername(username);

        if (member.isPresent()) {
            System.out.println("Member found with username: " + username);
        } else {
            System.out.println("No member found with username: " + username);
        }

        return member;
    }

    // FIND ALL MEMBERS
    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();

        if (members.isEmpty()) {
            System.out.println("No members found in the system.");
        }

        // Business rule: return only active members
        List<Member> activeMembers = new ArrayList<>();
        for (Member member : members) {
            if (member.isActive()) {  // assuming Member has isActive()
                activeMembers.add(member);
            }
        }

        return activeMembers;
    }

    // DELETE MEMBER
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Member ID cannot be null");
        }

        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            System.out.println("Cannot delete: Member with ID " + id + " does not exist.");
            return;
        }

        // Business rule: only inactive members can be deleted
        if (member.get().isActive()) {
            System.out.println("Cannot delete active member with ID " + id);
            return;
        }

        memberRepository.deleteById(id);
        System.out.println("Member with ID " + id + " has been deleted.");
    }
}
