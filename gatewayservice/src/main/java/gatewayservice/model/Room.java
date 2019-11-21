package gatewayservice.model;


import lombok.Data;

@Data
public class Room {
    private String hotelName;
    private String roomName;
    private Double price;
    private String status;
    private String fromDate;
    private String toDate;
}
