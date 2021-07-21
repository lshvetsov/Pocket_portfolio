package ru.redflag.pocketPortfolio.utils.validation;

import ru.redflag.pocketPortfolio.data.dto.PositionCreateDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositionValidator implements ConstraintValidator<ValidPosition, PositionCreateDto> {

    @Override
    public void initialize(ValidPosition constraintAnnotation) {

    }

    @Override
    public boolean isValid(PositionCreateDto positionCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        return positionCreateDto.getId() != null || positionCreateDto.getEquity() != null;
    }
}
