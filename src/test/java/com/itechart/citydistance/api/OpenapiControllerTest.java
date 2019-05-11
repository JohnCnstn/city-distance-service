package com.itechart.citydistance.api;

import com.itechart.citydistance.test.AbstractIntegrationTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OpenapiControllerTest extends AbstractIntegrationTest {

    @Test
    public void testOpenapiYaml() throws Exception {

        mockMvc.perform(get("/v1/openapi.yaml")).andExpect(status().isOk());
        mockMvc.perform(get("/v1/openapi.yml")).andExpect(status().isOk());

    }

    @Test
    public void testOpenapiJson() throws Exception {
        mockMvc.perform(get("/v1/openapi.json")).andExpect(status().isOk());
    }

}
