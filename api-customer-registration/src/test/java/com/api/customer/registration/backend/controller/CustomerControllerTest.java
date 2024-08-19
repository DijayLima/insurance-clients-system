package com.api.customer.registration.backend.controller;

import com.api.customer.registration.backend.domain.dto.AddressResponse;
import com.api.customer.registration.backend.domain.dto.CustomerResponse;
import com.api.customer.registration.backend.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();
    }

    @Test
    @DisplayName("Test to successfully find customer by id.")
    void testGetCustomerById_Success() throws Exception {
        CustomerResponse response = createSampleCustomerResponse();
        when(customerService.findCustomerByCpf(anyString())).thenReturn(response);

        mockMvc.perform(get("/api/customers/12345678901"))
                .andExpect(status().isOk())
                .andExpect(content().json(createSampleResponseJson()));
    }

    @Test
    @DisplayName("Test to successfully create customer.")
    void testCreateCustomer_Success() throws Exception {
        CustomerResponse response = createSampleCustomerResponse();
        when(customerService.createCustomer(any())).thenReturn(response);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createSampleRequestJson()))
                .andExpect(status().isCreated())
                .andExpect(content().json(createSampleResponseJson()));
    }

    @Test
    @DisplayName("Test to successfully update customer.")
    void testUpdateCustomer_Success() throws Exception {
        CustomerResponse response = createSampleCustomerResponse();
        when(customerService.update(anyString(), any())).thenReturn(response);

        mockMvc.perform(put("/api/customers/12345678901")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json(createSampleResponseJson()));
    }

    @Test
    @DisplayName("Test to successfully delete customer.")
    void testDeleteCustomer_Success() throws Exception {
        doNothing().when(customerService).deleteCustomer(anyString());

        mockMvc.perform(delete("/api/customers/12345678901"))
                .andExpect(status().isNoContent());
    }

    private CustomerResponse createSampleCustomerResponse() {
        return CustomerResponse.builder()
                .cpf("10110110107")
                .name("John Doe")
                .phone("(11) 985762345")
                .address(AddressResponse.builder()
                        .street("Rua Exemplo")
                        .number(123)
                        .complement("Apto 101")
                        .neighborhood("Centro")
                        .city("Cidade Exemplo")
                        .state("EX")
                        .cep("12345-678")
                        .build())
                .build();
    }

    private String createSampleRequestJson() {
        return "{\n" +
                "  \"cpf\": \"10110110107\",\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"phone\": \"(11) 985762345\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Rua Exemplo\",\n" +
                "    \"number\": \"123\",\n" +
                "    \"complement\": \"Apto 101\",\n" +
                "    \"neighborhood\": \"Centro\",\n" +
                "    \"city\": \"Cidade Exemplo\",\n" +
                "    \"state\": \"EX\",\n" +
                "    \"cep\": \"12345-678\"\n" +
                "  }\n" +
                "}";
    }

    private String createSampleResponseJson() {
        return "{\n" +
                "  \"cpf\": \"10110110107\",\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"phone\": \"(11) 985762345\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Rua Exemplo\",\n" +
                "    \"number\": 123,\n" +
                "    \"complement\": \"Apto 101\",\n" +
                "    \"neighborhood\": \"Centro\",\n" +
                "    \"city\": \"Cidade Exemplo\",\n" +
                "    \"state\": \"EX\",\n" +
                "    \"cep\": \"12345-678\"\n" +
                "  }\n" +
                "}";
    }
}
