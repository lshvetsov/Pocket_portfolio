package ru.redflag.pocketPortfolio.data.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dividend {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "position_id")
    @ToString.Exclude
    private Position position;

    private String operationId;

    @Enumerated(EnumType.STRING)
    private Status dividendStatus;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDate date;
    @Builder.Default
    private Double amountPerUnit = 0.0;
    @Builder.Default
    private Double totalAmount = 0.0;
    @Builder.Default
    private Double totalFee = 0.0;
    @Builder.Default
    private Double percentage = 0.0;

}
