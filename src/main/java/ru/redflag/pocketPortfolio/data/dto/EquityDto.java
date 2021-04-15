package ru.redflag.pocketPortfolio.data.dto;

import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.EquityArea;

@Data
@Builder
public class EquityDto {

    private String id;
    private EquityArea equityArea;
    private String country;
    private Currency currency;
    private Double currentPrice;

}
