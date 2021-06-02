package ru.redflag.pocketPortfolio.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.redflag.pocketPortfolio.data.enums.Broker;

@Data
public class PositionCreateDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EquityDto equity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Broker broker;

}
