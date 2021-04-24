package ru.redflag.pocketPortfolio.data.dto;

import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Equity;

@Data
@Builder
public class PositionDto {

    private String id;
    private Equity equity;
    private Broker broker;
    private Status status;
    private Integer amount;
    private Double initialCost;
    private Double currentCost;
}
