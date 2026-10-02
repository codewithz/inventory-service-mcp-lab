package com.training.inventory.exception;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolationException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void constraintViolationReturnsBadRequest() {
        ProblemDetail detail = handler.handleConstraintViolation(
                new ConstraintViolationException("productId must look like SKU-1001", Set.of()));

        assertThat(detail.getStatus()).isEqualTo(400);
    }
}
