package gatewayservice.service;

import gatewayservice.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@Slf4j
public class RoomService {

    @Autowired
    private KafkaService kafkaService;

    public void bookRoom(Room room) {
        log.info("Send room to kafka");
        kafkaService.sendToKafka(room);
    }

    public Room generateRandomRoom() {
        Room room = new Room();
        room.setHotelName("test_hotel");
        room.setRoomName(String.valueOf(new Random().nextInt()));
        room.setPrice(new Random().nextDouble());
        room.setStatus("booked");
        room.setFromDate("01.01.2020");
        room.setToDate("10.01.2020");
        return room;
    }
}
