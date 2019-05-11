package com.itechart.citydistance.util;

import com.github.javafaker.Faker;
import com.itechart.citydistance.generated.model.City;
import com.itechart.citydistance.generated.model.Road;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public abstract class TestUtil {

    public static final Faker FAKE = Faker.instance(Locale.US, ThreadLocalRandom.current());

    public static Road newRoad(City from, City to) {
        return new Road()
                .from(from)
                .to(to)
                .distance(FAKE.number().randomDouble(4, 1, 10000));
    }

    public static City newCity() {
        return new City()
                .name(FAKE.lorem().word());
    }

    public static City newCity(String name) {
        return new City()
                .name(name);
    }

}
