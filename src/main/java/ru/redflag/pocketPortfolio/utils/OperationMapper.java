package ru.redflag.pocketPortfolio.utils;

import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.model.Operation;

@Component
public class OperationMapper {

    public Operation toOperation (OperationDto operationDto) {
        return Operation.builder()
                .equity(operationDto.getEquity())
                .operationStatus(operationDto.getOperationStatus())
                .operationType(operationDto.getOperationType())
                .currency(operationDto.getCurrency())
                .date(operationDto.getDate())
                .amount(operationDto.getAmount())
                .fee(operationDto.getFee())
                .price(operationDto.getPrice()).build();
    }

    public OperationDto toOperationDto (Operation operation) {
        return OperationDto.builder()
                .id(operation.getId())
                .equity(operation.getEquity())
                .operationStatus(operation.getOperationStatus())
                .operationType(operation.getOperationType())
                .currency(operation.getCurrency())
                .amount(operation.getAmount())
                .fee(operation.getFee())
                .price(operation.getPrice())
                .build();
    }
}
