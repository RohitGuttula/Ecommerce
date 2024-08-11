package com.example.product.ExceptionHandler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
