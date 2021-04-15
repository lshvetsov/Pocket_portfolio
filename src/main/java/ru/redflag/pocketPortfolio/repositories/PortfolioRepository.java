package ru.redflag.pocketPortfolio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.redflag.pocketPortfolio.data.model.Portfolio;

public interface PortfolioRepository extends CrudRepository<Portfolio, String> {

}
