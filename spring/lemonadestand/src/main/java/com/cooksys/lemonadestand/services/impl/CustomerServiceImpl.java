package com.cooksys.lemonadestand.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Customer;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.mappers.CustomerMapper;
import com.cooksys.lemonadestand.model.CustomerRequestDto;
import com.cooksys.lemonadestand.model.CustomerResponseDto;
import com.cooksys.lemonadestand.repositories.CustomerRepository;
import com.cooksys.lemonadestand.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private void validateCustomerRequest(CustomerRequestDto customerRequestDto) {
        if (customerRequestDto.getName() == null || customerRequestDto.getPhoneNumber() == null) {
            throw new BadRequestException("All fields are required for creating a customer.");
        }
    }

    @Override
    public Customer getCustomerFromRequest(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findByIdAndDeletedFalse(id);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("No customer found with id: " + id);
        }
        return optionalCustomer.get();
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        validateCustomerRequest(customerRequestDto);
        return customerMapper.entityToResponseDto(customerRepository.saveAndFlush(customerMapper.requestDtoToEntity(customerRequestDto)));
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerMapper.entitiesToResponseDtos(customerRepository.findAllByDeletedFalse());
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        return customerMapper.entityToResponseDto(getCustomerFromRequest(id));
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
        if (customerRequestDto.getName() == null && customerRequestDto.getPhoneNumber() == null) {
            throw new BadRequestException("At least one field is required for updating a customer.");
        }
        Customer customer = getCustomerFromRequest(id);

        if (customer.getName() != null) {
            customer.setName(customerRequestDto.getName());
        }
        if (customer.getPhoneNumber() != null) {
            customer.setPhoneNumber(customerRequestDto.getPhoneNumber());
        }

        return customerMapper.entityToResponseDto(customerRepository.saveAndFlush(customer));
    }

    @Override
    public CustomerResponseDto deleteCustomer(Long id) {
        Customer customer = getCustomerFromRequest(id);
        customer.setDeleted(true);
        return customerMapper.entityToResponseDto(customerRepository.saveAndFlush(customer));
    }

}
