package ru.redflag.pocketPortfolio.data.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.OperationType;
import ru.redflag.pocketPortfolio.data.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operation {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "position_id")
    @ToString.Exclude
    private Position position;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    @ToString.Exclude
    private Portfolio portfolio;

    @Enumerated(EnumType.STRING)
    private Status operationStatus;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Basic
    private LocalDate date;

    @Builder.Default
    private Long amount = 0L;
    @Builder.Default
    private Double pricePerUnit = 0.0;
    @Builder.Default
    private Double totalPrice = 0.0;
    @Builder.Default
    private Double totalFee = 0.0;

}
