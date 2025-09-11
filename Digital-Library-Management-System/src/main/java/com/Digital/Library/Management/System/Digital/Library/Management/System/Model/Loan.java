package com.Digital.Library.Management.System.Digital.Library.Management.System.Model;

import com.Digital.Library.Management.System.Digital.Library.Management.System.Enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many loans → one member
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column( nullable = false)
    private LocalDate issueDate;

    @Column( nullable = false)
    private LocalDate dueDate;


    private LocalDate returnDate;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal fineAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING) // store enum as text (e.g. "ISSUED")
    @Column( nullable = false, length = 20)
    private Status status;  // ISSUED, RETURNED, LOST
}
