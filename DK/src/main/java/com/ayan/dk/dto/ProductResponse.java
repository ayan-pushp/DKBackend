package com.ayan.dk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        @JsonProperty("product_name")
        String productName,

        @JsonProperty("price")
        double price
) {
}
