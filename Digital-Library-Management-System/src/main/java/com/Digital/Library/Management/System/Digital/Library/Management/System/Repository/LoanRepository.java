package com.Digital.Library.Management.System.Digital.Library.Management.System.Repository;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Status;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Loan;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByMember(Member member);
    List<Loan> findByStatus(Status status);

}
