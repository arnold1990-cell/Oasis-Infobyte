package com.Digital.Library.Management.System.Digital.Library.Management.System.Mapper;

import com.Digital.Library.Management.System.Digital.Library.Management.System.DTO.LoanDto;
import com.Digital.Library.Management.System.Digital.Library.Management.System.Model.Loan;

public class LoanMapper {
    public LoanDto loanDto(Loan loan){
        if (loan == null) {return  null;

        }
        return  new LoanDto(
                loan.getId(),
                loan.getIssueDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getFineAmount(),
                loan.getStatus()
        );
    }
    public  Loan toEntity(LoanDto loanDto){
        if (loanDto == null) { return null;

        }
        Loan loan=new Loan();
        loan.setId(loanDto.id());
        loan.setIssueDate(loanDto.issueDate());
        loan.setDueDate(loanDto.dueDate());
        loan.setReturnDate(loanDto.returnDate());
        loan.setFineAmount(loanDto.fineAmount());
        loan.setStatus(loanDto.status());
        return  loan;
    }
}
