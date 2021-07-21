package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.PortfolioDto;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.model.Position;

import java.time.LocalDate;
import java.util.List;

public interface PortfolioService {

    PortfolioDto createPortfolio (String name, LocalDate term, Broker broker);
    PortfolioDto getPortfolio (String id);
    List<PortfolioDto> getAllPortfolios();
    void deletePortfolio (String id);
    void changeStatus (String id);
    PortfolioDto updateName (String id, String name);
    PortfolioDto updateHorizon(String id, LocalDate term);
    Position findPosition (String portfolioId, String ticker);

}
