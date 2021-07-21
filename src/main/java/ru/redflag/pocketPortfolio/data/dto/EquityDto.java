package ru.redflag.pocketPortfolio.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.EquityArea;
import ru.redflag.pocketPortfolio.data.enums.EquityType;
import ru.redflag.pocketPortfolio.data.enums.Exchange;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class EquityDto {

    private Exchange stockExchange = Exchange.Moscow;
    @NotNull
    private String ticker;
    private EquityArea equityArea;
    private Currency equityCurrency = Currency.RUB;
    private EquityType equityType;
    private String country;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double currentCostPerUnit;

}
