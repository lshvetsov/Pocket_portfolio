package ru.redflag.pocketPortfolio.utils.mappers;

import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.DividendDto;
import ru.redflag.pocketPortfolio.data.model.Dividend;

@Component
public class DividendMapper {

    public DividendDto dividendDto (Dividend dividend) {
        return DividendDto.builder()
                .dividendStatus(dividend.getDividendStatus())
                .currency(dividend.getCurrency())
                .date(dividend.getDate())
                .amountPerUnit(dividend.getAmountPerUnit())
                .totalAmount(dividend.getTotalAmount())
                .totalFee(dividend.getTotalFee())
                .percentage(dividend.getPercentage())
                .build();
    }

}
