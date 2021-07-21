package ru.redflag.pocketPortfolio.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.OperationType;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.utils.validation.ValidPosition;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class OperationCreateDto {

    @NotNull
    private PositionCreateDto position;
    @NotNull
    private OperationType operationType;
    private Status operationStatus = Status.ACTIVE;
    private Currency currency = Currency.RUB;

    private LocalDate date = LocalDate.now();

    private Long amount = 0L;
    @NotNull
    private Double pricePerUnit;
    private Double totalFee = 0.0;
}
