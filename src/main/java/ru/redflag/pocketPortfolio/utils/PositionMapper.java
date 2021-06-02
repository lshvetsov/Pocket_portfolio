package ru.redflag.pocketPortfolio.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.PositionDto;
import ru.redflag.pocketPortfolio.data.model.Position;

@Component
public class PositionMapper {

    @Autowired
    EquityMapper equityMapper;

    public PositionDto toPositionDto (Position position) {
        return PositionDto.builder()
                .id(position.getId())
                .equity(equityMapper.toEquityDto(position.getEquity()))
                .broker(position.getBroker())
                .status(position.getStatus())
                .amount(position.getAmount())
                .initialCost(position.getInitialCost())
                .initialCostPerUnit(position.getInitialCostPerUnit())
                .currentCost(position.getCurrentCost())
                .build();
    }

}
