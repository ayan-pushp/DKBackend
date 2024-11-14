package com.ayan.dk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CustomerUpdateRequest(
       @NotBlank(message = "First Name required")
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
        @JsonProperty("mobile")
        String mobile,

        @Size(max = 255, message = "Address must be a valid address")
        @JsonProperty("address")
         String address,

        @Pattern(regexp = "^\\d{6}$", message = "Pincode must be exactly 6 digits")
        @JsonProperty("pincode")
        String pincode
) {
}
