package com.itechart.citydistance.api;

import com.itechart.citydistance.config.AppProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DefaultRedirectController {

    private final AppProperties appProperties;

    @GetMapping({"/", "/v1"})
    public String defaultRedirect() {
        return "redirect:" + appProperties.getUrl().getSwaggerUi();
    }

}
