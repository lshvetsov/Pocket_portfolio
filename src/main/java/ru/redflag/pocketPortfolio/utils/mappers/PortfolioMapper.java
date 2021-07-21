package ru.redflag.pocketPortfolio.utils.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.PortfolioDto;
import ru.redflag.pocketPortfolio.data.model.Portfolio;
import ru.redflag.pocketPortfolio.data.model.Position;
import ru.redflag.pocketPortfolio.data.model.Purchase;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PortfolioMapper {

    @Autowired
    private PositionMapper positionMapper;

    public PortfolioDto toPortfolioDto(Portfolio portfolio){
        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto.setId(portfolio.getId());
        portfolioDto.setName(portfolio.getName());
        portfolioDto.setHorizon(portfolio.getHorizon());
        portfolioDto.setStatus(portfolio.getStatus());
        portfolioDto.setInitialCost(
                Optional.ofNullable(portfolio.getPositions()).stream()
                        .flatMap(Collection::stream)
                        .map(Position::getPurchaseLog)
                        .flatMap(Collection::stream)
                        .map(Purchase::getPurchaseCost)
                        .reduce(0.0, Double::sum));
        portfolioDto.setCurrentCost(
                Optional.ofNullable(portfolio.getPositions()).stream().flatMap(Collection::stream)
                        .map(Position::getCurrentCost)
                        .reduce(0.0, Double::sum));
        portfolioDto.setPositionAmount(portfolio.getPositions() == null ? 0L : (long) portfolio.getPositions().size());
        portfolioDto.setEquityAmount(
                Optional.ofNullable(portfolio.getPositions()).stream()
                        .flatMap(Collection::stream)
                        .map(Position::getAmount)
                        .count());
        if (portfolio.getPositions() != null) {
            portfolioDto.setPositions(
                    portfolio.getPositions().stream()
                            .map(positionMapper::toPositionDto)
                            .collect(Collectors.toList()));
        }
        return portfolioDto;
    }

}
