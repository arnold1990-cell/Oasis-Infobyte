package com.Digital.Library.Management.System.Digital.Library.Management.System.DTO;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record MemberDto(
         Long id,
 String username,
 String fullName,
String email,
         Role role

// getters/setters
) {
}
