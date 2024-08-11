package com.example.customer.Dto;

import com.example.customer.Entity.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
