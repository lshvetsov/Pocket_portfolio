package ru.redflag.pocketPortfolio.data.model;

import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;

import javax.persistence.*;

@Data
@Embeddable
public class Price {

    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", insertable = false, updatable = false)
    private Currency currency;

}
