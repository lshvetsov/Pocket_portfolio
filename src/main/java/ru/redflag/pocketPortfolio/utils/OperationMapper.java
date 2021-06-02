package ru.redflag.pocketPortfolio.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.OperationCreateDto;
import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.dto.PositionOperationDto;
import ru.redflag.pocketPortfolio.data.model.Operation;
import ru.redflag.pocketPortfolio.repositories.PositionRepository;

@Component
public class OperationMapper {

    @Autowired
    PositionMapper positionMapper;

    public Operation toOperation (OperationDto operationDto) {
        return Operation.builder()
                .operationStatus(operationDto.getOperationStatus())
                .operationType(operationDto.getOperationType())
                .currency(operationDto.getCurrency())
                .date(operationDto.getDate())
                .amount(operationDto.getAmount())
                .pricePerUnit(operationDto.getPricePerUnit())
                .totalPrice(operationDto.getTotalPrice())
                .totalFee(operationDto.getTotalFee())
                .build();
    }

    public Operation toOperation (OperationCreateDto operationDto) {
        return Operation.builder()
                .operationStatus(operationDto.getOperationStatus())
                .operationType(operationDto.getOperationType())
                .currency(operationDto.getCurrency())
                .date(operationDto.getDate())
                .amount(operationDto.getAmount())
                .pricePerUnit(operationDto.getPricePerUnit())
                .totalPrice(operationDto.getPricePerUnit()*operationDto.getAmount())
                .totalFee(operationDto.getTotalFee())
                .build();
    }

    public OperationDto toOperationDto (Operation operation) {
        return OperationDto.builder()
                .id(operation.getId())
                .portfolioName(operation.getPortfolio().getName())
                .position(PositionOperationDto.builder()
                        .id(operation.getPosition().getId())
                        .equityTicker(operation.getPosition().getEquity().getTicker())
                        .broker(operation.getPosition().getBroker())
                        .stockExchange(operation.getPosition().getEquity().getStockExchange())
                        .status(operation.getPosition().getStatus())
                        .build())
                .operationStatus(operation.getOperationStatus())
                .operationType(operation.getOperationType())
                .currency(operation.getCurrency())
                .amount(operation.getAmount())
                .pricePerUnit(operation.getPricePerUnit())
                .totalPrice(operation.getTotalPrice())
                .totalFee(operation.getTotalFee())
                .build();
    }


}
