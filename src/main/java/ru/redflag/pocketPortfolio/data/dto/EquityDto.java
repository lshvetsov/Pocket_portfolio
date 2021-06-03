package ru.redflag.pocketPortfolio.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.EquityArea;
import ru.redflag.pocketPortfolio.data.enums.EquityType;
import ru.redflag.pocketPortfolio.data.enums.Exchange;

@Data
@Builder
public class EquityDto {

    private String id;
    private Exchange stockExchange;
    private String ticker;
    private EquityArea equityArea;
    private Currency equityCurrency;
    private EquityType equityType;
    private String country;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double currentCostPerUnit;

}
