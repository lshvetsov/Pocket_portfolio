package ru.redflag.pocketPortfolio.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redflag.pocketPortfolio.data.dto.*;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Equity;
import ru.redflag.pocketPortfolio.data.model.Operation;
import ru.redflag.pocketPortfolio.data.model.Position;
import ru.redflag.pocketPortfolio.data.model.Purchase;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.errors.WrongActionException;
import ru.redflag.pocketPortfolio.repositories.EquityRepository;
import ru.redflag.pocketPortfolio.repositories.PortfolioRepository;
import ru.redflag.pocketPortfolio.repositories.PositionRepository;
import ru.redflag.pocketPortfolio.service.PositionService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;


@Service
@Slf4j
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
                positionDto.getEquity().getTicker(), positionDto.getEquity().getStockExchange()) == null ?
                createEquity(positionDto.getEquity()) :
                equityRepository.findByTickerAndStockExchange(positionDto.getEquity().getTicker(), positionDto.getEquity().getStockExchange());
        Position position = Position.builder()
                .equity(equity)
                .portfolio(portfolioRepository.findById(portfilioId).orElseThrow(ObjectNotFoundException::new))
                .broker(positionDto.getBroker())
                .purchaseLog(new ArrayList<>())
                .build();
        log.info("Position {} has added", position);
        return positionRepository.save(position);
    }

    @Override
    public Position changeStatus(String positionId, Status status) {
        if (Status.FUTURE.equals(status)) throw new WrongActionException();
        Position position = positionRepository.findById(positionId).orElseThrow(ObjectNotFoundException::new);

        log.info("Status of position {} has changed from {} to {}", position.getId(), position.getStatus(), status);

        position.setStatus(status);
        return positionRepository.save(position);
    }

    @Override
    public Position upatePosition (@NotNull Position position, @NotNull Operation operation) {
        switch (operation.getOperationType()) {
            case BUY:
                position.setAmount(position.getAmount() + operation.getAmount());
                position.setCurrentCost(position.getCurrentCost() + operation.getTotalPrice());
                addPurchaseToHistory(position, operation);
                break;
            case SELL:
                position.setAmount(position.getAmount() - operation.getAmount());
                position.setCurrentCost(position.getCurrentCost() - operation.getTotalPrice());
                addPurchaseToHistory(position, operation);
                break;
            default:
                throw new WrongActionException();
        }
        position.getEquity().setCurrentCostPerUnit(operation.getPricePerUnit());

        log.info("Position {} has been updated: action {}, current amount {}, current cost {}", position.getId(),
                operation.getOperationType(), position.getAmount(), position.getCurrentCost());

        return positionRepository.save(position);
    }

    @Override
    public Position findByPortfolioIdAndTicker (String portfolioId, String ticker) {
        return positionRepository.findPositionByPortfolioIdAndTicker(portfolioId, ticker);
    }

    @Override
    public Position findById(String positionId) {
        return positionRepository.findById(positionId).orElseThrow(ObjectNotFoundException::new);
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

    private void addPurchaseToHistory(Position position, Operation operation) {
        position.addToHistory(Purchase.builder()
                .date(operation.getDate())
                .currency(operation.getCurrency())
                .amount(position.getAmount())
                .currentCost(position.getCurrentCost())
                .purchaseAmount(operation.getAmount())
                .purchaseCost(operation.getTotalPrice())
                .purchaseFee(operation.getTotalFee())
                .purchaseCostPerUnit(operation.getPricePerUnit())
                .build());
    }

}
