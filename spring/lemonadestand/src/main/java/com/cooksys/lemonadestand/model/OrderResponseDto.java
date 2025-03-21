package com.cooksys.lemonadestand.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponseDto {

    private Long id;

    private double total;

    private List<LemonadeResponseDto> lemonades;

    private CustomerResponseDto customer;

    private LemonadeStandResponseDto lemonadeStand;
}
