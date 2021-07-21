package ru.redflag.pocketPortfolio.data.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Currency;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Purchase {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Basic
    LocalDate date;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Long amount = 0L;
    private Long purchaseAmount = 0L;
    private Double purchaseFee = 0.0;
    private Double purchaseCost = 0.0;
    private Double purchaseCostPerUnit = 0.0;
    private Double currentCost = 0.0;

}
