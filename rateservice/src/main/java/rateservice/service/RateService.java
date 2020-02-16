package rateservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rateservice.model.Rate;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@Service
public class RateService {

    private final List<Rate> staticRates = Collections.unmodifiableList(
            Arrays.asList(
                    generateRandomRate("Marriott"),
                    generateRandomRate("Radisson"),
                    generateRandomRate("HolidayInn"),
                    generateRandomRate("Sputnik"),
                    generateRandomRate("ibis")));

    private Rate generateRandomRate(String hotelName) {
        Rate rate = new Rate();
        rate.setHotelName(Optional.ofNullable(hotelName).orElse("unknown"));
        rate.setDate("10.01.2020");
        rate.setRate(ThreadLocalRandom.current().nextDouble(0.3,3.0));
        return rate;
    }

    public List<Rate> getStaticRates() {
        return staticRates;
    }

}
