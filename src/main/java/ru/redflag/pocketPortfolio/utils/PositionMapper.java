package ru.redflag.pocketPortfolio.utils;

import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.PositionDto;
import ru.redflag.pocketPortfolio.data.model.Position;

@Component
public class PositionMapper {

    public Position toPosition (PositionDto positionDto) {
        return Position.builder()
                .id(positionDto.getId())
                .equity(positionDto.getEquity())
                .currency(positionDto.getCurrency())
                .amount(positionDto.getAmount())
                .initialCost(positionDto.getInitialCost())
                .build();
    }

    public PositionDto toPositionDto (Position position) {
        return PositionDto.builder()
                .id(position.getId())
                .equity(position.getEquity())
                .status(position.getStatus())
                .currency(position.getCurrency())
                .amount(position.getAmount())
                .initialCost(position.getInitialCost())
                .currentCost(position.getCurrentCost())
                .build();
    }


}
