package ru.redflag.pocketPortfolio.data.model.EquityTypes;

import lombok.Data;
import ru.redflag.pocketPortfolio.data.model.Equity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Share")
public class Share extends Equity {

}
