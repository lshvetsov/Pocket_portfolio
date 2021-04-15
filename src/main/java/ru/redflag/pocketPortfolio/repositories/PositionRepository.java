package ru.redflag.pocketPortfolio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.redflag.pocketPortfolio.data.model.Position;

public interface PositionRepository extends CrudRepository<Position, String> {
}
