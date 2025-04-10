package com.cooksys.lemonadestand.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderRequestDto {

    private List<LemonadeRequestDto> lemonades;

    private CustomerRequestDto customer;

    private LemonadeStandDto lemonadeStand;
}
