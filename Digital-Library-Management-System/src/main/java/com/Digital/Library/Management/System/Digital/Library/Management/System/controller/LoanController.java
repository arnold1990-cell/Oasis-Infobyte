package com.Digital.Library.Management.System.Digital.Library.Management.System.controller;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Status;
import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Loan;
import com.Digital.Library.Management.System.Digital.Library.Management.System.model.Member;
import com.Digital.Library.Management.System.Digital.Library.Management.System.service.LoanService;
import com.Digital.Library.Management.System.Digital.Library.Management.System.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;
    private final MemberService memberService;

    @Autowired
    public LoanController(LoanService loanService, MemberService memberService) {
        this.loanService = loanService;
        this.memberService = memberService;
    }


    @PostMapping("/issue")
    public ResponseEntity<?> issueLoan(
            @RequestParam Long memberId,
            @RequestParam String issueDate,
            @RequestParam String dueDate
    ) {
        Optional<Member> memberOpt = memberService.findById(memberId);
        if (memberOpt.isEmpty()) {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }

        Member member = memberOpt.get();
        LocalDate issue = LocalDate.parse(issueDate);
        LocalDate due = LocalDate.parse(dueDate);

        Loan loan = loanService.issueLoan(member, issue, due);
        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }


    @PostMapping("/return/{loanId}")
    public ResponseEntity<?> returnLoan(
            @PathVariable Long loanId,
            @RequestParam String returnDate
    ) {
        LocalDate returnD = LocalDate.parse(returnDate);
        try {
            Loan loan = loanService.returnLoan(loanId, returnD);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getLoansByMember(@PathVariable Long memberId) {
        Optional<Member> memberOpt = memberService.findById(memberId);
        if (memberOpt.isEmpty()) {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }

        List<Loan> loans = loanService.findLoansByMember(memberOpt.get());
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable Status status) {
        List<Loan> loans = loanService.findLoansByStatus(status);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.findAll();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long loanId) {
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>("Delete operation completed", HttpStatus.OK);
    }
}

