package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.OperationCreateDto;
import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.enums.Status;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {

    public OperationDto addOperation (String portfolioId, OperationCreateDto operationDto);
    public OperationDto updateOperationStatus (String operationId, Status status);
    public OperationDto updateOperation (String operationId, OperationDto operationDto);
    public List<OperationDto> getAllOperations();
    public OperationDto getOperation (String id);

    public List<OperationDto> searchByParameters ();
    public List<OperationDto> searchByDates (LocalDate from, LocalDate to);


}
