package rateservice.model;


import lombok.Data;

@Data
public class Rate {
    private String hotelName;
    private Double rate;
    private String date;
}
