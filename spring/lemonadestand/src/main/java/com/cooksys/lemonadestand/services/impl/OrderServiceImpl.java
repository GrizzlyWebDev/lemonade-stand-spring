package com.cooksys.lemonadestand.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Customer;
import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.entities.LemonadeStand;
import com.cooksys.lemonadestand.entities.Order;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.mappers.CustomerMapper;
import com.cooksys.lemonadestand.mappers.LemonadeMapper;
import com.cooksys.lemonadestand.mappers.OrderMapper;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.OrderRequestDto;
import com.cooksys.lemonadestand.model.OrderResponseDto;
import com.cooksys.lemonadestand.repositories.CustomerRepository;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.repositories.LemonadeStandRepository;
import com.cooksys.lemonadestand.repositories.OrderRepository;
import com.cooksys.lemonadestand.services.CustomerService;
import com.cooksys.lemonadestand.services.LemonadeService;
import com.cooksys.lemonadestand.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;

    private final LemonadeRepository lemonadeRepository;

    private final OrderRepository orderRepository;

    private final LemonadeService lemonadeService;

    private final LemonadeMapper lemonadeMapper;

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    private final LemonadeStandRepository lemonadeStandRepository;

    private final OrderMapper orderMapper;

    private Order setUpOrder(OrderRequestDto order) {
        double total = 0.0;

        Customer newCustomer = customerRepository.saveAndFlush(customerMapper.requestDtoToEntity(order.getCustomer()));

        Optional<LemonadeStand> selectedStand = lemonadeStandRepository.findByNameAndDeletedFalse(order.getLemonadeStand().getName());
        if (selectedStand.isEmpty()) {
            throw new BadRequestException("No lemonade stand with that name found.");
        }
        Order newOrder = orderMapper.requestDtoToEntity(order);
        newOrder.setCustomer(newCustomer);
        newOrder.setLemonadeStand(selectedStand.get());
        newOrder = orderRepository.saveAndFlush(newOrder);
        List<Lemonade> lemonades = new ArrayList<>();
        for (LemonadeRequestDto lemonade : order.getLemonades()) {
            Lemonade newLemonade = lemonadeMapper.lemonadeResponseDtoToEntity(lemonadeService.createLemonade(lemonade));
            newLemonade.setOrder(newOrder);
            lemonadeRepository.saveAndFlush(newLemonade);
            lemonades.add(newLemonade);
            total += newLemonade.getPrice();
        }

        newOrder.setTotal(total);
        newOrder.setLemonades(lemonades);
        orderRepository.saveAndFlush(newOrder);
        return newOrder;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = setUpOrder(orderRequestDto);
        return orderMapper.entityToResponseDto(order);
    }

}
