package com.example;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class MessageValidator {

    private final Validator validator;

    /**
     * Для проектов без Spring: сам создаст Validator.
     */
    public MessageValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }

    /**
     * Для Spring-проектов: инжектни jakarta.validation.Validator.
     */
    public MessageValidator(Validator validator) {
        if (validator == null) {
            throw new IllegalArgumentException("Validator cannot be null");
        }
        this.validator = validator;
    }

    public <T extends ValidatableMessage> void validate(T message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        Set<ConstraintViolation<T>> violations = validator.validate(message);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining(", "));

            throw new IllegalArgumentException("Invalid " + message.objectType() + ": " + errors);
        }
    }
}