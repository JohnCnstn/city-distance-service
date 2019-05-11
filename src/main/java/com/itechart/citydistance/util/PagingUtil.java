package com.itechart.citydistance.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PagingUtil {

    public static Pageable toPageable(int page, int size) {
        return toPageable(page, size, Sort.unsorted());
    }

    public static Pageable toPageable(int page, int size, Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }


}
