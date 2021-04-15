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

    public Portfolio toPortfolio(PortfolioDto portfolioDto) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioDto.getId());
        portfolio.setName(portfolioDto.getName());
        portfolio.setHorizon(portfolioDto.getHorizon());
        portfolio.setBroker(portfolioDto.getBroker());
        portfolio.setStatus(portfolioDto.getStatus());
        portfolio.setPositions(portfolioDto.getPositions());
        return portfolio;

    }

    public PortfolioDto toPortfolioDto(Portfolio portfolio){
        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto.setId(portfolio.getId());
        portfolioDto.setName(portfolio.getName());
        portfolioDto.setHorizon(portfolio.getHorizon());
        portfolioDto.setBroker(portfolio.getBroker());
        portfolioDto.setStatus(portfolio.getStatus());
        portfolioDto.setPositions(portfolio.getPositions());
        portfolioDto.setInitialCost(
                Optional.ofNullable(portfolioDto.getPositions())
                        .map(Collection::stream)
                        .orElseGet(Stream::empty)
                        .map(Position::getInitialCost)
                        .reduce(0.0, Double::sum));
        portfolioDto.setCurrentCost(
                Optional.ofNullable(portfolioDto.getPositions())
                        .map(Collection::stream)
                        .orElseGet(Stream::empty)
                        .map(Position::getCurrentCost)
                        .reduce(0.0, Double::sum));
        portfolioDto.setPositionAmount(portfolioDto.getPositions() == null ? 0L : (long) portfolioDto.getPositions().size());
        portfolioDto.setEquityAmount(
                Optional.ofNullable(portfolioDto.getPositions())
                        .map(Collection::stream)
                        .orElseGet(Stream::empty)
                        .map(Position::getAmount)
                        .count());
        return portfolioDto;
    }

}
