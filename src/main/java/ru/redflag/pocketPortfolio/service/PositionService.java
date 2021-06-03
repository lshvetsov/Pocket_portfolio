package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.dto.PositionCreateDto;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Position;

public interface PositionService {

    Position addPosition (String portfilioId, PositionCreateDto positionDto);
    Position changeStatus (String portfilioId, Status status);
    Position upatePosition (String portfilioId, OperationDto operation);
    Position findByPortfolioIdAndTicker (String portfolioId, String ticker);

}
