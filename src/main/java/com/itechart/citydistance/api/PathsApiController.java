package com.itechart.citydistance.api;

import com.itechart.citydistance.generated.api.PathsApi;
import com.itechart.citydistance.generated.model.Path;
import com.itechart.citydistance.service.PathService;
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
public class PathsApiController implements PathsApi {

    private final PathService pathService;

    @Override
    public ResponseEntity<List<Path>> getPaths(
            @NotNull @Valid String from,
            @NotNull @Valid String to,
            @Min(1) @Valid Integer page,
            @Min(1) @Max(100) @Valid Integer size) {
        var paths = pathService.getPaths(from, to, toPageable(page, size));
        var headers = generatePaginationHeaders(paths);
        return new ResponseEntity<>(paths.getContent(), headers, HttpStatus.OK);
    }

}
