package ru.redflag.pocketPortfolio.data.dto;

import lombok.Builder;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Status;

@Data
@Builder
public class PositionDto {

    private String id;
    private EquityDto equity;
    private Broker broker;
    private Status status;
    private Long amount;
    private Double currentCost;

}
