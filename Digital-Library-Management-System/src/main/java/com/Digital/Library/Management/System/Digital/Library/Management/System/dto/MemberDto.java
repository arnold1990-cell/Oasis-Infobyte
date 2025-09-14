package com.Digital.Library.Management.System.Digital.Library.Management.System.dto;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Role;

public record MemberDto(
         Long id,
 String username,
 String fullName,
String email,
         Role role

) {
}
