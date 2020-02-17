package gatewayservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Room {
    @JsonProperty("hotel_name")
    private String hotelName;
    @JsonProperty("room_name")
    private String roomName;
    private Double price;
    private String status;
    @JsonProperty("from_date")
    private String fromDate;
    @JsonProperty("to_date")
    private String toDate;
}
