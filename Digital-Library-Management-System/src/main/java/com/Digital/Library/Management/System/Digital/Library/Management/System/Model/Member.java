package com.Digital.Library.Management.System.Digital.Library.Management.System.Model;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false,unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role; // "ADMIN" or "USER"
    private boolean active = true;
    // getters/setters
}

