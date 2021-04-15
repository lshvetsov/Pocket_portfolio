package ru.redflag.pocketPortfolio.data.dto;

import lombok.*;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Currency;
import ru.redflag.pocketPortfolio.data.enums.Status;
import ru.redflag.pocketPortfolio.data.model.Position;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Data
public class PortfolioDto {

    private String id;
    private String name;
    private LocalDate horizon;
    private Broker broker;
    private Status status;
    private Double initialCost;
    private Double currentCost;
    private Long equityAmount;
    private Long positionAmount;
    private List<Position> positions;

}
