package ru.redflag.pocketPortfolio.data.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.Status;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Builder
public class Position {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "equity_id")
    private Equity equity;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Integer amount = 0;
    private Double initialCost;
    private Double currentCost = 0.0;

}
