package com.itechart.citydistance.api;

import com.itechart.citydistance.generated.api.RoadsApi;
import com.itechart.citydistance.generated.model.Road;
import com.itechart.citydistance.service.RoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class RoadsApiController implements RoadsApi {

    private final RoadService roadService;

    @Override
    public ResponseEntity<Road> createRoad(@Valid Road road) {
        var created = roadService.createRoad(road);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

}
