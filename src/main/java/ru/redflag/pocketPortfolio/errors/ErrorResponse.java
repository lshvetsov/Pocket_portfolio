package ru.redflag.pocketPortfolio.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private Timestamp timestamp;

}
