package ru.redflag.pocketPortfolio.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Status;
import javax.persistence.*;
import java.util.List;

/***
 * Уровень брокера
 */


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Broker broker;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private Integer amount = 0;
    private Double initialCost = 0.0;
    private Double initialCostPerUnit = 0.0;
    private Double currentCost = 0.0;

    @OneToMany(mappedBy = "position")
    private List<Operation> operations;
    @OneToMany(mappedBy = "position")
    private List<Dividend> dividends;

}
