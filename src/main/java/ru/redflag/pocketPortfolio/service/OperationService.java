package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.OperationDto;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {

    public OperationDto addOperation (OperationDto operationDto);
    public void updateOperationStatus (OperationDto operationDto);
    public List<OperationDto> searchByParameters ();
    public List<OperationDto> searchByDates (LocalDate from, LocalDate to);


}
