package ru.redflag.pocketPortfolio.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.redflag.pocketPortfolio.errors.AmbigiousChoiceException;
import ru.redflag.pocketPortfolio.errors.ErrorResponse;
import ru.redflag.pocketPortfolio.errors.ObjectNotFoundException;
import ru.redflag.pocketPortfolio.errors.WrongActionException;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseBody ErrorResponse
    handleNotFound () {
        return new ErrorResponse("Object not found", Timestamp.from(Instant.now()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(WrongActionException.class)
    @ResponseBody ErrorResponse
    handleWrongAction () {
        return new ErrorResponse("Wrong action", Timestamp.from(Instant.now()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(AmbigiousChoiceException.class)
    @ResponseBody ErrorResponse
    handleAmbigiousChoice () {
        return new ErrorResponse("There are several options and it's impossible to choose the only one",
                Timestamp.from(Instant.now()));

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody ErrorResponse
    hadleAllOthers () {
        return new ErrorResponse("InternalError", Timestamp.from(Instant.now()));
    }
}
