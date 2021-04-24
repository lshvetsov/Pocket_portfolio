package ru.redflag.pocketPortfolio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redflag.pocketPortfolio.data.dto.PortfolioDto;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Portfolio;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.repositories.PortfolioRepository;
import ru.redflag.pocketPortfolio.service.PortfolioService;
import ru.redflag.pocketPortfolio.utils.PortfolioMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
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
        portfolioRepository.save(portfolio);
    }

    @Override
    public PortfolioDto updateName(String id, String name) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        portfolio.setName(name);
        portfolioRepository.save(portfolio);
        return portfolioMapper.toPortfolioDto(portfolio);
    }

    @Override
    public PortfolioDto updateHorizon(String id, LocalDate horizon) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        portfolio.setHorizon(horizon);
        portfolioRepository.save(portfolio);
        return portfolioMapper.toPortfolioDto(portfolio);
    }
}
