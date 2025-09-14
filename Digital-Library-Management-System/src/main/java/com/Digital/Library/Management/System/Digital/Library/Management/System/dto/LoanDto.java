package com.Digital.Library.Management.System.Digital.Library.Management.System.dto;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanDto(
         Long id,
         LocalDate issueDate,
         LocalDate dueDate,
         LocalDate returnDate,
         BigDecimal fineAmount, Status status
) {
}
