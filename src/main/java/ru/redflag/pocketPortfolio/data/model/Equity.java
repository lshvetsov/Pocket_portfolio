package ru.redflag.pocketPortfolio.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.*;

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

    @Enumerated(EnumType.STRING)
    private Exchange stockExchange;
    private String ticker;

    private String country;
    @Enumerated(EnumType.STRING)
    private EquityArea equityArea;
    @Enumerated(EnumType.STRING)
    private EquityType equityType;
    @Enumerated(EnumType.STRING)
    private Currency equityCurrency;

    private Double currentCostPerUnit = 0.0;  //TODO Implement the process of an automatic update of the equity cost

    @OneToMany(mappedBy = "equity")
    private List<Position> includedPositions;

}
