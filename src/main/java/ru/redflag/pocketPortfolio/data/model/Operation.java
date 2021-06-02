package ru.redflag.pocketPortfolio.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Position position;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @Enumerated(EnumType.STRING)
    private Status operationStatus;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDate date;

    private Long amount = 0L;
    private Double pricePerUnit = 0.0;
    private Double totalPrice = 0.0;
    private Double totalFee = 0.0;

}
