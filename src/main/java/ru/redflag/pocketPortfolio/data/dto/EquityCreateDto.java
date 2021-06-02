package ru.redflag.pocketPortfolio.data.dto;

import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.EquityArea;
import ru.redflag.pocketPortfolio.data.enums.Exchange;

@Data
public class EquityCreateDto {

    private Exchange stockExchange;
    private String ticker;
    private EquityArea equityArea;
    private String country;
    private Currency equityCurrency;

}
