package ru.redflag.pocketPortfolio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.redflag.pocketPortfolio.data.enums.Exchange;
import ru.redflag.pocketPortfolio.data.model.Equity;

public interface EquityRepository extends CrudRepository<Equity, String> {

    Equity findByTickerAndStockExchange(String ticker, Exchange stockExchange);

}
