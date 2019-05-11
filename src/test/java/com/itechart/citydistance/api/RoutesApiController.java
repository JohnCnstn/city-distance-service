package com.itechart.citydistance.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.itechart.citydistance.generated.model.City;
import com.itechart.citydistance.generated.model.Route;
import com.itechart.citydistance.test.AbstractIntegrationTest;
import com.itechart.citydistance.util.TestUtil;
import org.junit.Test;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoutesApiController extends AbstractIntegrationTest {

    @Test
    public void testGetRoutes_happyPath() throws Exception {
        // GIVEN
        var first = TestUtil.newCity("city1");
        var second = TestUtil.newCity("city2");
        var third = TestUtil.newCity("city3");
        var forth = TestUtil.newCity("city4");
        createRoad(first, second);
        createRoad(second, third);
        createRoad(first, forth);
        createRoad(forth, third);

        // WHEN
        var response = mockMvc.perform(
                get(URI.create("/api/v1/routes"))
                        .contentType(APPLICATION_JSON)
                        .param("from", "city1")
                        .param("to", "city3"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        List<Route> result = objectMapper.readValue(response.getContentAsByteArray(), new TypeReference<List<Route>>() {
        });

        // THEN
        assertSoftly(softly -> {
            softly.assertThat(result.size()).isEqualTo(2);
        });

    }

    @Test
    public void testGetRoutes_unhappyPath() throws Exception {
        // GIVEN
        var first = TestUtil.newCity("city1");
        var second = TestUtil.newCity("city2");
        var third = TestUtil.newCity("city3");
        var forth = TestUtil.newCity("city4");
        createRoad(first, second);
        createRoad(third, forth);

        // WHEN
        var response = mockMvc.perform(
                get(URI.create("/api/v1/routes"))
                        .contentType(APPLICATION_JSON)
                        .param("from", "city1")
                        .param("to", "city3"))
                .andExpect(status().isUnprocessableEntity())
                .andReturn().getResponse();
        List<Route> result = objectMapper.readValue(response.getContentAsByteArray(), new TypeReference<List<Route>>() {
        });

        // THEN
        assertSoftly(softly -> {
            softly.assertThat(result.size()).isEqualTo(2);
        });

    }

    private void createRoad(City from, City to) throws Exception {
        var request = TestUtil.newRoad(from, to);
        var response = mockMvc.perform(
                post(URI.create("/api/v1/roads"))
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
    }

}
