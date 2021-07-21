package ru.redflag.pocketPortfolio.repositories.Impl;

import org.springframework.stereotype.Repository;
import ru.redflag.pocketPortfolio.data.model.Position;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.repositories.CustomPositionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class CustomPositionRepositoryImpl implements CustomPositionRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Position findPositionByPortfolioIdAndTicker(String portfolioId, String ticker) {

        Query query = entityManager.createNativeQuery("select * from position " +
                        "inner join equity on position.equity_id = equity.id " +
                "where position.portfolio_id = ? and equity.ticker = ?", Position.class);
        query.setParameter(1, portfolioId);
        query.setParameter(2, ticker);

        if (query.getResultList().size() == 0) return null;
        else return (Position) query.getResultList().get(0);
    }
}
