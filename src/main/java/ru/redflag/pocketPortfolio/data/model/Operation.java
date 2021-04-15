package ru.redflag.pocketPortfolio.data.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Operation {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "equity_id")
    private Equity equity;

    @Enumerated(EnumType.STRING)
    private Status operationStatus;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDate date;
    private Long amount = 0L;
    private Double price;
    private Double fee;

}
