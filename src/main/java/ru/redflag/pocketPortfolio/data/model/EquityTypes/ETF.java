package ru.redflag.pocketPortfolio.data.model.EquityTypes;

import ru.redflag.pocketPortfolio.data.model.Equity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ETF")
public class ETF extends Equity {
}
