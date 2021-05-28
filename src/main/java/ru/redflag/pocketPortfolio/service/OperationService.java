package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.enums.Status;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {

    public OperationDto addOperation (String portfolioId, OperationDto operationDto);
    public OperationDto updateOperationStatus (String id, Status status);
    public List<OperationDto> getAllOperations();
    public OperationDto getOperation (String id);
    public List<OperationDto> searchByParameters ();
    public List<OperationDto> searchByDates (LocalDate from, LocalDate to);


}
