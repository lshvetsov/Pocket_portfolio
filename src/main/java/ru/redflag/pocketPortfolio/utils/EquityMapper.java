package ru.redflag.pocketPortfolio.utils;

import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.EquityDto;
import ru.redflag.pocketPortfolio.data.model.Equity;

@Component
public class EquityMapper {

    EquityDto toEquityDto (Equity equity) {
        return EquityDto.builder()
                .id(equity.getId())
                .stockExchange(equity.getStockExchange())
                .ticker(equity.getTicker())
                .country(equity.getCountry())
                .equityArea(equity.getEquityArea())
                .equityCurrency(equity.getEquityCurrency())
                .build();
    }

}
