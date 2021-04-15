package ru.redflag.pocketPortfolio.utils;

import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.EquityDto;
import ru.redflag.pocketPortfolio.data.model.Equity;

@Component
public class EquityMapper {

    Equity toEquity (EquityDto equityDto) {
        return Equity.builder()
                .id(equityDto.getId())
                .country(equityDto.getCountry())
                .equityArea(equityDto.getEquityArea())
                .currency(equityDto.getCurrency())
                .currentPrice(equityDto.getCurrentPrice())
                .build();
    }

    EquityDto toEquityDto (Equity equity) {
        return EquityDto.builder()
                .id(equity.getId())
                .country(equity.getCountry())
                .equityArea(equity.getEquityArea())
                .currency(equity.getCurrency())
                .currentPrice(equity.getCurrentPrice())
                .build();
    }

}
