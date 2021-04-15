package ru.redflag.pocketPortfolio.data.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Dividend extends Operation {

    private Double percentage;

}
