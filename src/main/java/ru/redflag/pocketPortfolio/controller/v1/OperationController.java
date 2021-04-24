package ru.redflag.pocketPortfolio.controller.v1;

import ru.redflag.pocketPortfolio.data.dto.OperationDto;

import java.time.LocalDate;
import java.util.List;

public class OperationController {

    public OperationDto addOperation (String portfolioId, OperationDto operationDto) {
        return null;
    }

    public OperationDto addDividend (String portfolioId, OperationDto operationDto) {
        return null;
    }

    public void inactivateOperation (String operationId) {

    }

    public void confirmOperation (String operationId) {

    }
    public List<OperationDto> searchOperationByParameters () {
        return null;
    }
    public List<OperationDto> searchOperationByDates (LocalDate from, LocalDate to) {
        return null;
    }

}
