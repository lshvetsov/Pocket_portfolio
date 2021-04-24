package ru.redflag.pocketPortfolio.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.EquityArea;
import ru.redflag.pocketPortfolio.data.enums.EquityType;
import javax.persistence.*;
import java.util.List;

/***
 * Уровень биржи
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String country;
    @Enumerated(EnumType.STRING)
    private EquityArea equityArea;
    @Enumerated(EnumType.STRING)
    private EquityType equityType;
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
