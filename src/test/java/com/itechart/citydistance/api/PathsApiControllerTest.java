package com.itechart.citydistance.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.itechart.citydistance.generated.model.Path;
import com.itechart.citydistance.generated.model.Road;
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

public class PathsApiControllerTest extends AbstractIntegrationTest {

    @Test
    public void testGetPaths_happyPath() throws Exception {
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
                get(URI.create("/api/v1/paths"))
                        .contentType(APPLICATION_JSON)
                        .param("from", firstCityName)
                        .param("to", thirdCityName))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        List<Path> result = objectMapper.readValue(response.getContentAsByteArray(), new TypeReference<List<Path>>() {
        });

        // THEN
        var firstPathTotalDistance = firstToSecondDistance + secondToThirdDistance;
        var secondPathTotalDistance = firstToForthDistance + forthToThirdDistance;
        assertSoftly(softly -> {
            softly.assertThat(result.size()).isEqualTo(2);
            //first path
            softly.assertThat(result.get(0).getTotalDistance()).isEqualTo(firstPathTotalDistance);
            softly.assertThat(result.get(0).getCities().get(0).getName()).isEqualTo(firstCityName);
            softly.assertThat(result.get(0).getCities().get(1).getName()).isEqualTo(secondCityName);
            softly.assertThat(result.get(0).getCities().get(2).getName()).isEqualTo(thirdCityName);
            //second path
            softly.assertThat(result.get(1).getTotalDistance()).isEqualTo(secondPathTotalDistance);
            softly.assertThat(result.get(1).getCities().get(0).getName()).isEqualTo(firstCityName);
            softly.assertThat(result.get(1).getCities().get(1).getName()).isEqualTo(forthCityName);
            softly.assertThat(result.get(1).getCities().get(2).getName()).isEqualTo(thirdCityName);
        });

    }

    @Test
    public void testGetPathsWithCycledCities() throws Exception {
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

        final int secondToForthDistance = 5;

        var firstRoad = TestUtil.newRoad(first, second, firstToSecondDistance);
        createRoad(firstRoad);
        var secondRoad = TestUtil.newRoad(second, third, secondToThirdDistance);
        createRoad(secondRoad);
        var thirdRoad = TestUtil.newRoad(first, forth, firstToForthDistance);
        createRoad(thirdRoad);
        var forthRoad = TestUtil.newRoad(forth, third, forthToThirdDistance);
        createRoad(forthRoad);
        var fifthRoad = TestUtil.newRoad(second, forth, secondToForthDistance);
        createRoad(fifthRoad);

        // WHEN
        var response = mockMvc.perform(
                get(URI.create("/api/v1/paths"))
                        .contentType(APPLICATION_JSON)
                        .param("from", firstCityName)
                        .param("to", thirdCityName))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        List<Path> result = objectMapper.readValue(response.getContentAsByteArray(), new TypeReference<List<Path>>() {
        });

        // THEN
        var firstPathTotalDistance = firstToSecondDistance + secondToThirdDistance;
        var secondPathTotalDistance = firstToForthDistance + forthToThirdDistance;
        var thirdPathTotalDistance = firstToSecondDistance + secondToForthDistance + forthToThirdDistance;
        assertSoftly(softly -> {
            softly.assertThat(result.size()).isEqualTo(4);
            //first path
            softly.assertThat(result.get(0).getTotalDistance()).isEqualTo(firstPathTotalDistance);
            softly.assertThat(result.get(0).getCities().get(0).getName()).isEqualTo(firstCityName);
            softly.assertThat(result.get(0).getCities().get(1).getName()).isEqualTo(secondCityName);
            softly.assertThat(result.get(0).getCities().get(2).getName()).isEqualTo(thirdCityName);
            //second path
            softly.assertThat(result.get(1).getTotalDistance()).isEqualTo(secondPathTotalDistance);
            softly.assertThat(result.get(1).getCities().get(0).getName()).isEqualTo(firstCityName);
            softly.assertThat(result.get(1).getCities().get(1).getName()).isEqualTo(forthCityName);
            softly.assertThat(result.get(1).getCities().get(2).getName()).isEqualTo(thirdCityName);
            //third path
            softly.assertThat(result.get(2).getTotalDistance()).isEqualTo(thirdPathTotalDistance);
            softly.assertThat(result.get(2).getCities().get(0).getName()).isEqualTo(firstCityName);
            softly.assertThat(result.get(2).getCities().get(1).getName()).isEqualTo(secondCityName);
            softly.assertThat(result.get(2).getCities().get(2).getName()).isEqualTo(forthCityName);
            softly.assertThat(result.get(2).getCities().get(3).getName()).isEqualTo(thirdCityName);
            //forth path
            softly.assertThat(result.get(3).getTotalDistance()).isEqualTo(result.get(2).getTotalDistance());
            softly.assertThat(result.get(3).getCities().get(0).getName()).isEqualTo(firstCityName);
            softly.assertThat(result.get(3).getCities().get(1).getName()).isEqualTo(forthCityName);
            softly.assertThat(result.get(3).getCities().get(2).getName()).isEqualTo(secondCityName);
            softly.assertThat(result.get(3).getCities().get(3).getName()).isEqualTo(thirdCityName);
        });

    }

    @Test
    public void testGetPathsWhileCitiesDontExist() throws Exception {
        // GIVEN
        final String firstCityName = "city1";
        final String secondCityName = "city2";

        // WHEN
        var resultActions = mockMvc.perform(
                get(URI.create("/api/v1/paths"))
                        .contentType(APPLICATION_JSON)
                        .param("from", firstCityName)
                        .param("to", secondCityName));
        // THEN
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void testGetPathsBetweenNotConnectedCities() throws Exception {
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
        var resultActions = mockMvc.perform(
                get(URI.create("/api/v1/paths"))
                        .contentType(APPLICATION_JSON)
                        .param("from", firstCityName)
                        .param("to", forthCityName));
        // THEN
        resultActions.andExpect(status().isUnprocessableEntity());
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
