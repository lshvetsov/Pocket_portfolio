package ru.redflag.pocketPortfolio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.redflag.pocketPortfolio.data.model.Operation;

public interface OperationRepository extends CrudRepository<Operation, String> {
}
