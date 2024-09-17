package com.rest.jwt_auth.validator;

import static com.rest.jwt_auth.utils.Constants.PASSWORD_NOT_MATCHING;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PasswordMatchingValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface PasswordMatching {

    String message() default PASSWORD_NOT_MATCHING;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
