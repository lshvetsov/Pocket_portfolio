package ru.redflag.pocketPortfolio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redflag.pocketPortfolio.data.dto.*;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Equity;
import ru.redflag.pocketPortfolio.data.model.Position;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.repositories.EquityRepository;
import ru.redflag.pocketPortfolio.repositories.PortfolioRepository;
import ru.redflag.pocketPortfolio.repositories.PositionRepository;
import ru.redflag.pocketPortfolio.service.PositionService;

import javax.validation.constraints.NotNull;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    EquityRepository equityRepository;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    PositionRepository positionRepository;

    @Override
    public Position addPosition(String portfilioId, PositionCreateDto positionDto) {
        Equity equity = equityRepository.findByTickerAndStockExchange(
                positionDto.getEquity().getTicker(), positionDto.getEquity().getStockExchange()) == null
                ? createEquity(positionDto.getEquity()) : equityRepository.findByTickerAndStockExchange(
                positionDto.getEquity().getTicker(), positionDto.getEquity().getStockExchange());
        Position position = Position.builder()
                .equity(equity)
                .portfolio(portfolioRepository.findById(portfilioId).orElseThrow(ObjectNotFoundException::new))
                .broker(positionDto.getBroker())
                .build();
        return positionRepository.save(position);
    }

    @Override
    public Position changeStatus(String portfilioId, Status status) {
        return null;
    }

    @Override
    public Position upatePosition(String portfilioId, OperationDto operation) {
        return null;
    }

    @Override
    public Position findByPortfolioIdAndTicker (String portfolioId, String ticker) {
        return positionRepository.findPositionByPortfolioIdAndTicker(portfolioId, ticker);
    }

    @NotNull
    private Equity createEquity (EquityDto equityDto) {
        Equity equity = Equity.builder()
                .stockExchange(equityDto.getStockExchange())
                .ticker(equityDto.getTicker())
                .equityArea(equityDto.getEquityArea())
                .equityCurrency(equityDto.getEquityCurrency())
                .equityType(equityDto.getEquityType())
                .country(equityDto.getCountry())
                .build();
        return equityRepository.save(equity);
    }
}
