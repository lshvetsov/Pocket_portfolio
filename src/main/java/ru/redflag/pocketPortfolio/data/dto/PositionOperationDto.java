package ru.redflag.pocketPortfolio.data.dto;

import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Exchange;
import ru.redflag.pocketPortfolio.data.enums.Status;

@Data
@Builder
public class PositionOperationDto {

    private String id;
    private String equityTicker;
    private Broker broker;
    private Exchange stockExchange;
    private Status status;

}
