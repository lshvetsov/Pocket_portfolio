package ru.redflag.pocketPortfolio.data.dto;

import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.Status;

import java.time.LocalDate;

@Data
@Builder
public class DividendDto {

    private Status dividendStatus;
    private Currency currency;
    private LocalDate date;
    private Double amountPerUnit;
    private Double totalAmount;
    private Double totalFee;
    private Double percentage;

}
