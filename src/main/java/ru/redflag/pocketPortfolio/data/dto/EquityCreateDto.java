package ru.redflag.pocketPortfolio.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.EquityArea;
import ru.redflag.pocketPortfolio.data.enums.EquityType;
import ru.redflag.pocketPortfolio.data.enums.Exchange;

@Data
public class EquityCreateDto {

    private Exchange stockExchange;
    private String ticker;
    private EquityArea equityArea;
    private EquityType equityType;
    private Currency equityCurrency;
    private String country;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double currentCostPerUnit;

}
