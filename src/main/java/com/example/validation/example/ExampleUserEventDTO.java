package com.example.validation.example;

import com.example.validation.ValidatableMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleUserEventDTO implements ValidatableMessage {
    @NotBlank(message = "eventId is required")
    private String eventId;

    @Email(message = "email must be valid")
    private String email;

    @NotBlank(message = "action is required")
    @Size(max = 30, message = "action must be <= 30 chars")
    private String action;

    // getters/setters-lombok

    /**
     * Опционально: если нужно фиксированное имя в ошибках.
     */
    @Override
    public String objectType() {
        return "UserEvent";
    }
}
