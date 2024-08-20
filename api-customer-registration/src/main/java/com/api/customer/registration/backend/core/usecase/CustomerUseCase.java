package com.api.customer.registration.backend.core.usecase;

import com.api.customer.registration.backend.configuration.exception.CustomerException;
import com.api.customer.registration.backend.core.entity.CustomerRequest;
import com.api.customer.registration.backend.core.entity.CustomerRequestUpdate;
import com.api.customer.registration.backend.core.entity.CustomerResponse;
import com.api.customer.registration.backend.core.gateway.CustomerGateway;
import com.api.customer.registration.backend.dataprovider.mapper.CustomerMapper;
import com.api.customer.registration.backend.dataprovider.model.Customer;
import com.api.customer.registration.backend.dataprovider.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerUseCase implements CustomerGateway {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customer) {
        log.info("[createCustomer] - begin");
        Optional<Customer> byCpf = customerRepository.findByCpf(customer.getCpf());
        if (byCpf.isPresent()) {
            log.error("Customer with CPF " + customer.getCpf() + " already exists.");
            throw new CustomerException("Customer with CPF " + customer.getCpf() + " already exists.");
        }
        Customer customerSaved = customerRepository.save(customerMapper.toEntity(customer));
        log.info("[createCustomer] - end");
        return customerMapper.toDTO(customerSaved);
    }

    @Override
    public CustomerResponse update(String cpf, CustomerRequestUpdate customer) {
        log.info("[update] - begin");
        Customer existingCustomer = getExistingCustomerByCpf(cpf);
        BeanUtils.copyProperties(customerMapper.toEntity(cpf, customer), existingCustomer, "cpf", "createAt");
        Customer customerUpdated = customerRepository.save(existingCustomer);
        log.info("[update] - end");
        return customerMapper.toDTO(customerUpdated);
    }

    @Override
    public CustomerResponse findCustomerByCpf(String cpf) {
        log.info("[findCustomerByCpf] - begin");
        Customer customer = getExistingCustomerByCpf(cpf);
        log.info("[findCustomerByCpf] - end");
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(String cpf) {
        log.info("[deleteCustomer] - begin");
        findCustomerByCpf(cpf);
        customerRepository.deleteByCpf(cpf);
        log.info("[deleteCustomer] - end");
    }

    private Customer getExistingCustomerByCpf(String cpf) {
        return customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerException("Customer with CPF " + cpf + " not exists."));
    }
}

