package com.ayan.dk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("email")
        String email,
        @JsonProperty("mobile")
        String mobile,
        @JsonProperty("address")
        String address,
        @JsonProperty("pincode")
        String pincode

) {
}
