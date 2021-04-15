package ru.redflag.pocketPortfolio.data.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.EquityArea;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "EQUITY_TYPE", length = 10)
@Data
@Builder
@NoArgsConstructor
public abstract class Equity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private EquityArea equityArea;
    private String country;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Double currentPrice;

    @OneToMany(mappedBy = "equity")
    private List<Position> includedPositions;
    @OneToMany(mappedBy = "equity")
    private List<Operation> operations;
    @OneToMany(mappedBy = "equity")
    private List<Dividend> dividends;

}
