package rateservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rate {
    @JsonProperty("hotel_name")
    private String hotelName;
    private Double rate;
    @JsonProperty("rate_date")
    private String rateDate;
}
