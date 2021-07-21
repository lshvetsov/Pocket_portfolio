package ru.redflag.pocketPortfolio.utils.validation;

import  javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { PositionValidator.class })
@Documented
public @interface ValidPosition {

    String message() default "It should be position.id or position.equity property";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
