package ru.redflag.pocketPortfolio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.redflag.pocketPortfolio.data.model.Dividend;

import java.util.List;

public interface DividendRepository extends CrudRepository<Dividend, String> {

    List<Dividend> findByPositionId (String positionId);
    Dividend findByOperationId (String operationId);

}
