package ru.redflag.pocketPortfolio.data.dto;

import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Equity;

@Data
@Builder
public class PositionDto {

    private String id;
    private Equity equity;
    private Status status;
    private Currency currency;
    private Integer amount;
    private Double initialCost;
    private Double currentCost;
}
