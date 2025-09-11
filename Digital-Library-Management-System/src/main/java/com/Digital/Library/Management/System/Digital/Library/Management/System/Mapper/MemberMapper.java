package com.Digital.Library.Management.System.Digital.Library.Management.System.Mapper;

import com.Digital.Library.Management.System.Digital.Library.Management.System.DTO.MemberDto;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Member;

public class MemberMapper {
    public MemberDto memberDto(Member member) {
        if (member == null) {
            return null;

        }
        return new MemberDto(
                member.getId(),
                member.getUsername(),
                member.getFullName(),
                member.getEmail(),
                member.getRole()
        );

    }

    public Member toEntity(MemberDto memberDto) {
        if (memberDto == null) {
            return null;
        }
            Member member = new Member();
            member.setId(memberDto.id());
            member.setUsername(memberDto.username());
            member.setFullName(memberDto.fullName());
            member.setEmail(memberDto.email());
            member.setRole(memberDto.role());
            return member;
        }



}
