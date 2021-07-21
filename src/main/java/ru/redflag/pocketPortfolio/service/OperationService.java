package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.OperationCreateDto;
import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.enums.Status;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {

    OperationDto addOperation (String portfolioId, OperationCreateDto operationDto);
    OperationDto updateOperationStatus (String operationId, Status status);
    OperationDto updateOperation (String operationId, OperationDto operationDto);
    List<OperationDto> getAllOperations();
    OperationDto getOperation(String id);
    List<OperationDto> getOperationByPortfolio (String portfolioId);

    List<OperationDto> searchByDates (String portfolioId, LocalDate from, LocalDate to);


}
