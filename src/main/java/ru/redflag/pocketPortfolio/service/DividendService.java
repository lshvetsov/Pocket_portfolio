package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.DividendDto;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Dividend;
import ru.redflag.pocketPortfolio.data.model.Operation;
import ru.redflag.pocketPortfolio.data.model.Position;

import java.time.LocalDate;
import java.util.List;

public interface DividendService {

    Dividend addDividend (Position position, Operation operation);
    Dividend updateDividendStatus (String dividendId, Status status);
    DividendDto getDividend (String id);
    List<Dividend> getDividendsBydPosition (String positionId, LocalDate from, LocalDate to);
    Dividend findByOperationId (String operationId);

}
