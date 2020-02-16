package rateservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rateservice.model.Rate;

import java.util.*;


@Slf4j
@Service
public class RateService {

    private final List<Rate> staticRates = Collections.unmodifiableList(
            Arrays.asList(
                    generateRandomRate("test_hotel"),
                    generateRandomRate("test_hote2")));

    private Rate generateRandomRate(String hotelName) {
        Rate rate = new Rate();
        rate.setHotelName(Optional.ofNullable(hotelName).orElse("test_hotel"));
        rate.setDate("10.01.2020");
        rate.setRate(new Random().nextDouble());
        return rate;
    }

    public List<Rate> getStaticRates() {
        return staticRates;
    }

}
