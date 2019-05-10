package com.itechart.citydistance.api;

import com.itechart.citydistance.generated.model.Road;
import com.itechart.citydistance.test.AbstractIntegrationTest;
import com.itechart.citydistance.util.TestUtil;
import org.junit.Test;

import java.net.URI;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoadsApiControllerTest extends AbstractIntegrationTest {

    @Test
    public void testCreateRoad_happyPath() throws Exception {
        // GIVEN
        var request = TestUtil.newRoad();

        // WHEN
        var response = mockMvc.perform(
                post(URI.create("/v1/roads"))
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        var result = objectMapper.readValue(response.getContentAsByteArray(), Road.class);

        // THEN
        assertSoftly(softly -> {
//            softly.assertThat(result.getId()).isNotNull();
            softly.assertThat(result.getDistance()).isEqualTo(request.getDistance());
        });

    }

}
