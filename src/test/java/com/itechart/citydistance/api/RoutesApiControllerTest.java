package com.itechart.citydistance.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.itechart.citydistance.generated.model.Road;
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

public class RoutesApiControllerTest extends AbstractIntegrationTest {

    @Test
    public void testGetRoutes_happyPath() throws Exception {
        // GIVEN
        final String firstCityName = "city1";
        final String secondCityName = "city2";
        final String thirdCityName = "city3";
        final String forthCityName = "city4";

        var first = TestUtil.newCity(firstCityName);
        var second = TestUtil.newCity(secondCityName);
        var third = TestUtil.newCity(thirdCityName);
        var forth = TestUtil.newCity(forthCityName);

        final int firstToSecondDistance = 1;
        final int secondToThirdDistance = 2;
        final int firstToForthDistance = 3;
        final int forthToThirdDistance = 4;

        var firstRoad = TestUtil.newRoad(first, second, firstToSecondDistance);
        createRoad(firstRoad);
        var secondRoad = TestUtil.newRoad(second, third, secondToThirdDistance);
        createRoad(secondRoad);
        var thirdRoad = TestUtil.newRoad(first, forth, firstToForthDistance);
        createRoad(thirdRoad);
        var forthRoad = TestUtil.newRoad(forth, third, forthToThirdDistance);
        createRoad(forthRoad);

        // WHEN
        var response = mockMvc.perform(
                get(URI.create("/api/v1/routes"))
                        .contentType(APPLICATION_JSON)
                        .param("from", firstCityName)
                        .param("to", thirdCityName))
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
        final String firstCityName = "city1";
        final String secondCityName = "city2";
        final String thirdCityName = "city3";
        final String forthCityName = "city4";

        var first = TestUtil.newCity(firstCityName);
        var second = TestUtil.newCity(secondCityName);
        var third = TestUtil.newCity(thirdCityName);
        var forth = TestUtil.newCity(forthCityName);

        var firstRoad = TestUtil.newRoad(first, second);
        createRoad(firstRoad);
        var secondRoad = TestUtil.newRoad(third, forth);
        createRoad(secondRoad);

        // WHEN
        mockMvc.perform(
                get(URI.create("/api/v1/routes?page=1&size=20&from=a&to=b"))
                        .contentType(APPLICATION_JSON))
                // THEN
                .andExpect(status().isUnprocessableEntity());
    }

    private void createRoad(Road road) throws Exception {
        var response = mockMvc.perform(
                post(URI.create("/api/v1/roads"))
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(road)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
    }

}
