package com.itechart.citydistance.util;

import com.github.javafaker.Faker;
import com.itechart.citydistance.generated.model.City;
import com.itechart.citydistance.generated.model.Road;
import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class TestUtil {

    public static final Faker FAKE = Faker.instance(Locale.US, ThreadLocalRandom.current());

    public static Road newRoad() {
        return new Road()
                .from(newCity())
                .to(newCity())
                .distance(FAKE.number().randomDouble(4, 1, 10000));
    }

    public static City newCity() {
        return new City()
                .name(FAKE.lorem().word());
    }

}
