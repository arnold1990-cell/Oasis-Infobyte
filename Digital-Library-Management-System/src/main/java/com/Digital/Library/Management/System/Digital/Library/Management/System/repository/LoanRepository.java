package com.Digital.Library.Management.System.Digital.Library.Management.System.repository;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Status;
import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Loan;
import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByMember(Member member);
    List<Loan> findByStatus(Status status);

}
