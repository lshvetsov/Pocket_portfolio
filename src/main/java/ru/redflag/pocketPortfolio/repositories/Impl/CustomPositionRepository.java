package ru.redflag.pocketPortfolio.repositories.Impl;

import org.springframework.stereotype.Repository;
import ru.redflag.pocketPortfolio.data.model.Position;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class CustomPositionRepository implements ru.redflag.pocketPortfolio.repositories.CustomPositionRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Position findPositionByPortfolioIdAndTicker(String portfolioId, String ticker) {

        Query query = entityManager.createNativeQuery("select * from position " +
                        "inner join equity e on p.equity_id = e.id " +
                "where p.portfolio_id = ? and e.ticker = ?", Position.class);
        query.setParameter(1, portfolioId);
        query.setParameter(2, ticker);

        return (Position) query.getResultList().get(0);

    }
}
