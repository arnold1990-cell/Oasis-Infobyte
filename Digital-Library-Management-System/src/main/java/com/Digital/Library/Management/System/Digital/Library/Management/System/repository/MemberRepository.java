package com.Digital.Library.Management.System.Digital.Library.Management.System.repository;

import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsername(String username);
}
