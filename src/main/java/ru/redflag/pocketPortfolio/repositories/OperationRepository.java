package ru.redflag.pocketPortfolio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.redflag.pocketPortfolio.data.model.Operation;

import java.util.List;

public interface OperationRepository extends CrudRepository<Operation, String> {

    List<Operation> findByPortfolioId (String portfolioId);
}
