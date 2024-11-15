package com.ayan.dk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
        @NotBlank(message = "Product Name should be present")
        @JsonProperty("product_name")
        String productName,

        @JsonProperty("price")
        double price
) {
}
