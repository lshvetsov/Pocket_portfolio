package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.PositionCreateDto;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Operation;
import ru.redflag.pocketPortfolio.data.model.Position;

public interface PositionService {

    Position addPosition (String portfilioId, PositionCreateDto positionDto);
    Position changeStatus (String positionId, Status status);
    Position upatePosition (Position position, Operation operation);

    Position findByPortfolioIdAndTicker (String portfolioId, String ticker);
    Position findById (String positionId);


}
