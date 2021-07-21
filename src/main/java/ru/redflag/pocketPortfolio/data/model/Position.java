package ru.redflag.pocketPortfolio.data.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Status;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @ToString.Exclude
    private Equity equity;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    @ToString.Exclude
    private Portfolio portfolio;

    @Enumerated(EnumType.STRING)
    private Broker broker;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    @Builder.Default
    private Long amount = 0L;
    @Builder.Default
    private Double currentCost = 0.0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "purchase_history",
            joinColumns = {@JoinColumn(name = "position_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "purchase_id", referencedColumnName = "id")})
    @ToString.Exclude
    private List<Purchase> purchaseLog;

    @OneToMany(mappedBy = "position")
    @ToString.Exclude
    private List<Operation> operations;
    @OneToMany(mappedBy = "position")
    @ToString.Exclude
    private List<Dividend> dividends;

    public void addToHistory (Purchase purchase) {
        purchaseLog.add(purchase);
    }

}
