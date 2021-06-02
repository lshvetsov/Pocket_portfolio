package ru.redflag.pocketPortfolio.service;

import ru.redflag.pocketPortfolio.data.dto.EquityDto;
import ru.redflag.pocketPortfolio.data.dto.PortfolioDto;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.model.Portfolio;
import ru.redflag.pocketPortfolio.data.model.Position;

import java.time.LocalDate;
import java.util.List;

public interface PortfolioService {

    public PortfolioDto createPortfolio (String name, LocalDate term, Broker broker);
    public PortfolioDto getPortfolio (String id);
    public List<PortfolioDto> getAllPortfolios();
    public void deletePortfolio (String id);
    public void changeStatus (String id);
    public PortfolioDto updateName (String id, String name);
    public PortfolioDto updateHorizon(String id, LocalDate term);
    public Position findPosition (String portfolioId, String ticker);

}
