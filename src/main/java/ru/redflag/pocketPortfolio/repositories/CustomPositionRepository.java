package ru.redflag.pocketPortfolio.repositories;

import ru.redflag.pocketPortfolio.data.model.Position;

public interface CustomPositionRepository {

    Position findPositionByPortfolioIdAndTicker (String portfolioId, String ticker);

}
