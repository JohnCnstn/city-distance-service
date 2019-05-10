package com.itechart.citydistance.util;

import lombok.experimental.UtilityClass;

import static java.lang.System.getenv;
import static org.apache.commons.lang3.StringUtils.firstNonEmpty;

@UtilityClass
public class VersionUtil {

    public static String getAppVersion() {
        return firstNonEmpty(getenv("APP_VERSION"), "unknown");
    }

}
