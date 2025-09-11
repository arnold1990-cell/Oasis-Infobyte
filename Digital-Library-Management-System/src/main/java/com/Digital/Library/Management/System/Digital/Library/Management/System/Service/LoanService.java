package com.Digital.Library.Management.System.Digital.Library.Management.System.Service;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Status;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Loan;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Member;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // -------------------- ISSUE LOAN --------------------
    public Loan issueLoan(Member member, LocalDate issueDate, LocalDate dueDate) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        if (issueDate == null || dueDate == null) {
            throw new IllegalArgumentException("Issue date and due date cannot be null");
        }
        if (dueDate.isBefore(issueDate)) {
            throw new IllegalArgumentException("Due date cannot be before issue date");
        }

        Loan loan = new Loan();
        loan.setMember(member);
        loan.setIssueDate(issueDate);
        loan.setDueDate(dueDate);
        loan.setStatus(Status.ISSUED);
        loan.setFineAmount(BigDecimal.ZERO);

        return loanRepository.save(loan);
    }

    // RETURN LOAN
    public Loan returnLoan(Long loanId, LocalDate returnDate) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isEmpty()) {
            throw new RuntimeException("Loan with ID " + loanId + " not found");
        }

        Loan loan = optionalLoan.get();

        if (loan.getStatus() != Status.ISSUED) {
            throw new RuntimeException("Loan is not currently issued and cannot be returned");
        }

        loan.setReturnDate(returnDate);
        loan.setStatus(Status.RETURNED);

        // Calculate fine if overdue
        if (returnDate.isAfter(loan.getDueDate())) {
            long daysLate = returnDate.toEpochDay() - loan.getDueDate().toEpochDay();
            BigDecimal fine = BigDecimal.valueOf(daysLate).multiply(BigDecimal.valueOf(1)); // 1 unit per day late
            loan.setFineAmount(fine);
        }

        return loanRepository.save(loan);
    }

    // FIND LOANS BY MEMBER
    public List<Loan> findLoansByMember(Member member) {
        if (member == null) {
            return new ArrayList<>();
        }
        return loanRepository.findByMember(member);
    }

    //  FIND LOANS BY STATUS
    public List<Loan> findLoansByStatus(Status status) {
        if (status == null) {
            return new ArrayList<>();
        }
        return loanRepository.findByStatus(status);
    }

    //  DELETE LOAN
    public void deleteLoan(Long loanId) {
        Optional<Loan> loan = loanRepository.findById(loanId);
        if (loan.isEmpty()) {
            System.out.println("Loan with ID " + loanId + " does not exist.");
            return;
        }

        loanRepository.deleteById(loanId);
        System.out.println("Loan with ID " + loanId + " deleted.");
    }

    //  FIND ALL LOANS
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }
}
