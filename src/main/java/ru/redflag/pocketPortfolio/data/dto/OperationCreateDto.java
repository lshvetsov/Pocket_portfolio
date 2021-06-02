package ru.redflag.pocketPortfolio.data.dto;

import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.OperationType;
import ru.redflag.pocketPortfolio.data.enums.Status;

import java.time.LocalDate;

@Data
@Builder
public class OperationCreateDto {

    private PositionCreateDto position;

    private OperationType operationType;
    private Status operationStatus;
    private Currency currency;

    private LocalDate date;

    private Long amount = 0L;
    private Double pricePerUnit;
    private Double totalFee;
}
