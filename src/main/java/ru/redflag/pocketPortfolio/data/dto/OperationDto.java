package ru.redflag.pocketPortfolio.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.OperationType;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Equity;
import ru.redflag.pocketPortfolio.data.model.Position;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class OperationDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String portfolioName;
    private PositionOperationDto position;

    private OperationType operationType;
    private Status operationStatus;
    private Currency currency;

    private LocalDate date;

    @Min(value = 0, message = "Must be greater or equals to 0")
    private Long amount = 0L;
    @Min(value = 0, message = "Must be greater or equals to 0")
    private Double pricePerUnit;
    @Min(value = 0, message = "Must be greater or equals to 0")
    private BigDecimal totalPrice;
    @Min(value = 0, message = "Must be greater or equals to 0")
    private Double totalFee;
}
