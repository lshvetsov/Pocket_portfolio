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
    private Position position;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDate date;
    private Double amountPerUnit = 0.0;
    private Double totalAmount = 0.0;
    private Double totalFee = 0.0;
    private Double percentage = 0.0;

}
