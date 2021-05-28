package ru.redflag.pocketPortfolio.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.OperationType;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Equity;
import java.time.LocalDate;

@Data
@Builder
public class OperationDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    private Equity equity;
    private OperationType operationType;
    private Status operationStatus;
    private Currency currency;
    private LocalDate date;
    private Long amount;
    private Double price;
    private Double fee;
}
