package ru.redflag.pocketPortfolio.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redflag.pocketPortfolio.data.dto.PortfolioDto;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Portfolio;
import ru.redflag.pocketPortfolio.data.model.Position;
import ru.redflag.pocketPortfolio.errors.AmbigiousChoiceException;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.repositories.PortfolioRepository;
import ru.redflag.pocketPortfolio.service.PortfolioService;
import ru.redflag.pocketPortfolio.utils.mappers.PortfolioMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    PortfolioMapper portfolioMapper;

    @Override
    public PortfolioDto createPortfolio(String name, LocalDate horizon, Broker broker) {
        Portfolio portfolio = portfolioRepository.save(Portfolio.builder()
                .name(name)
                .horizon(horizon)
                .build());
        log.info("Portfolio {} has been created", portfolio);
        return portfolioMapper.toPortfolioDto(portfolio);
    }
    @Override
    public PortfolioDto getPortfolio(String id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        return portfolioMapper.toPortfolioDto(portfolio);
    }
    @Override
    public List<PortfolioDto> getAllPortfolios() {
        List<PortfolioDto> portfolios = new ArrayList<>();
        portfolioRepository.findAll().forEach(x -> portfolios.add(portfolioMapper.toPortfolioDto(x)));
        return portfolios;
    }
    @Override
    public void deletePortfolio(String id) {
        log.info("Portfolio {} has deleted", id);
        portfolioRepository.deleteById(id);
    }
    @Override
    public void changeStatus(String id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        switch (portfolio.getStatus()) {
            case ACTIVE:
                portfolio.setStatus(Status.INACTIVE);
                break;
            case INACTIVE:
                portfolio.setStatus(Status.ACTIVE);
                break;
            default: break;
        }
        log.info("Status of portfolio {} has been updated", id);
        portfolioRepository.save(portfolio);
    }
    @Override
    public PortfolioDto updateName(String id, String name) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        log.info("Portfolio {} name has changed from {} to {}", id, portfolio.getName(), name);

        portfolio.setName(name);
        portfolioRepository.save(portfolio);
        return portfolioMapper.toPortfolioDto(portfolio);
    }
    @Override
    public PortfolioDto updateHorizon(String id, LocalDate horizon) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        log.info("Portfolio {} horizon has been changed from {} to {}", id, portfolio.getHorizon(), horizon);

        portfolio.setHorizon(horizon);
        portfolioRepository.save(portfolio);
        return portfolioMapper.toPortfolioDto(portfolio);
    }
    @Override
    public Position findPosition(String portfolioId, String ticker) { //TODO
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow(ObjectNotFoundException::new);
        List<Position> positions = portfolio.getPositions().stream()
                .filter(x -> x.getEquity().getTicker().equals(ticker))
                .collect(Collectors.toList());
        if (positions.size() == 1) {
            return positions.get(0);
        } else {
            throw new AmbigiousChoiceException();
        }
    }


}
