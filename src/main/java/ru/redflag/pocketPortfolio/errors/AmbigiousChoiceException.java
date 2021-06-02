package ru.redflag.pocketPortfolio.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AmbigiousChoiceException extends RuntimeException {
}
