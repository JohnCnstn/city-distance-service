package com.itechart.citydistance.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

@UtilityClass
public final class HeaderUtil {

    public static final String X_TOTAL_COUNT = "X-Total-Count";

    public static <T> MultiValueMap<String, String> generatePaginationHeaders(Page<T> page) {
        var headers = new HttpHeaders();
        headers.add(X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
        return headers;
    }

}
