package ru.redflag.pocketPortfolio.service.Impl;

import ru.redflag.pocketPortfolio.data.dto.OperationDto;
import ru.redflag.pocketPortfolio.data.dto.PositionDto;
import ru.redflag.pocketPortfolio.data.enums.Status;

public interface PositionService {

    PositionDto addPosition (String portfilioId, PositionDto positionDto);
    PositionDto changeStatus (String portfilioId, Status status);
    PositionDto upatePosition (String portfilioId, OperationDto operation);

}
