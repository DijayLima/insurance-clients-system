package com.api.insurances.backend.entrypoint;

import com.api.insurances.backend.core.entity.AddressResponse;
import com.api.insurances.backend.core.entity.CustomerResponse;
import com.api.insurances.backend.core.entity.InsuranceResponse;
import com.api.insurances.backend.core.entity.InsuranceTypeResponse;
import com.api.insurances.backend.core.gateway.InsuranceGateway;
import com.api.insurances.backend.dataprovider.enumeration.InsuranceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InsuranceControllerTest {

    @Mock
    private InsuranceGateway insuranceUseCase;

    @InjectMocks
    private InsuranceController insuranceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(insuranceController)
                .build();
    }

    @Test
    @DisplayName("Test to successfully contract insurance.")
    void testCreateInsurance() throws Exception {
        InsuranceResponse response = createSampleInsuranceResponse();
        when(insuranceUseCase.hireInsurance(any())).thenReturn(response);

        mockMvc.perform(post("/api/insurance/hiring")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createSampleRequestJson()))
                .andExpect(status().isCreated())
                .andExpect(content().json(createSampleResponseJson()));
    }

    @Test
    @DisplayName("Test for successful insurance contracting simulation.")
    void testSimulateInsurance() throws Exception {
        InsuranceResponse response = createSampleInsuranceResponse();
        when(insuranceUseCase.simulateInsurance(any())).thenReturn(response);

        mockMvc.perform(post("/api/insurance/simulate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createSampleRequestJson()))
                .andExpect(status().isOk())
                .andExpect(content().json(createSampleResponseJson()));
    }

    private InsuranceResponse createSampleInsuranceResponse() {
        return InsuranceResponse.builder()
                .customer(CustomerResponse.builder()
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
                        .build())
                .insurance(InsuranceTypeResponse.builder()
                        .insurance(InsuranceType.BRONZE)
                        .id(1)
                        .name("Seguro de Bronze")
                        .description("Básico")
                        .build())
                .valueMonthly(99.99)
                .build();
    }

    private String createSampleRequestJson() {
        return "{\n" +
                "  \"customerId\": \"10110110107\",\n" +
                "  \"insurance\": 1\n" +
                "}";
    }

    private String createSampleResponseJson() {
        return "{\n" +
                "  \"customer\": {\n" +
                "    \"cpf\": \"10110110107\",\n" +
                "    \"name\": \"John Doe\",\n" +
                "    \"phone\": \"(11) 985762345\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Rua Exemplo\",\n" +
                "      \"number\": 123,\n" +
                "      \"complement\": \"Apto 101\",\n" +
                "      \"neighborhood\": \"Centro\",\n" +
                "      \"city\": \"Cidade Exemplo\",\n" +
                "      \"state\": \"EX\",\n" +
                "      \"cep\": \"12345-678\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"insurance\": {\n" +
                "    \"insurance\": \"BRONZE\",\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Seguro de Bronze\",\n" +
                "    \"description\": \"Básico\"\n" +
                "  },\n" +
                "  \"valueMonthly\": 99.99\n" +
                "}";
    }
}
