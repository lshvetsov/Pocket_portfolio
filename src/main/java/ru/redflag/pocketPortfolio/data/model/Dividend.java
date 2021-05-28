package ru.redflag.pocketPortfolio.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Dividend extends Operation {

    private Double percentage;

}
