package gatewayservice.model;


import java.util.Optional;

public class Room {

    private final String hotelName;
    private final String roomName;
    private final Double price;
    private final String status;
    private final String fromDate;
    private final String toDate;


    public Room(String hotelName, String roomName, Double price, String status, String fromDate, String toDate) {
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.price = price;
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getRoomName() {
        return roomName;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }
}
