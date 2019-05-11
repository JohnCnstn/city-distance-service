package com.itechart.citydistance.api;

import com.itechart.citydistance.generated.api.RoutesApi;
import com.itechart.citydistance.generated.model.Route;
import com.itechart.citydistance.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.itechart.citydistance.util.HeaderUtil.generatePaginationHeaders;
import static com.itechart.citydistance.util.PagingUtil.toPageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoutesApiController implements RoutesApi {

    private final RouteService routeService;

    @Override
    public ResponseEntity<List<Route>> getRoutes(
            @NotNull @Valid String from,
            @NotNull @Valid String to,
            @Min(1) @Valid Integer page,
            @Min(1) @Max(100) @Valid Integer size) {
        var routes = routeService.getRoutes(from, to, toPageable(page, size));
        var headers = generatePaginationHeaders(routes);
        return new ResponseEntity<>(routes.getContent(), headers, HttpStatus.OK);
    }

}
