package ru.redflag.pocketPortfolio.utils;

import org.springframework.stereotype.Component;
import ru.redflag.pocketPortfolio.data.dto.PortfolioDto;
import ru.redflag.pocketPortfolio.data.model.Portfolio;
import ru.redflag.pocketPortfolio.data.model.Position;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class PortfolioMapper {

    public PortfolioDto toPortfolioDto(Portfolio portfolio){
        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto.setId(portfolio.getId());
        portfolioDto.setName(portfolio.getName());
        portfolioDto.setHorizon(portfolio.getHorizon());
        portfolioDto.setStatus(portfolio.getStatus());
        portfolioDto.setPositions(portfolio.getPositions());
        portfolioDto.setInitialCost(
                Optional.ofNullable(portfolioDto.getPositions()).stream().flatMap(Collection::stream)
                        .map(Position::getInitialCost)
                        .reduce(0.0, Double::sum));
        portfolioDto.setCurrentCost(
                Optional.ofNullable(portfolioDto.getPositions()).stream().flatMap(Collection::stream)
                        .map(Position::getCurrentCost)
                        .reduce(0.0, Double::sum));
        portfolioDto.setPositionAmount(portfolioDto.getPositions() == null ? 0L : (long) portfolioDto.getPositions().size());
        portfolioDto.setEquityAmount(
                Optional.ofNullable(portfolioDto.getPositions()).stream().flatMap(Collection::stream)
                        .map(Position::getAmount)
                        .count());
        return portfolioDto;
    }

}
