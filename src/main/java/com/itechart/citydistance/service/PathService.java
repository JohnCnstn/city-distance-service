package com.itechart.citydistance.service;

import com.itechart.citydistance.generated.model.Path;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PathService {

    Page<Path> getPaths(String from, String to, Pageable pageable);

}
