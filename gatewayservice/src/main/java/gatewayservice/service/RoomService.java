package gatewayservice.service;

import gatewayservice.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RoomService {

    @Autowired
    private KafkaService kafkaService;

    public void bookRoom(Room room) {
        log.info("Send room to kafka");
    }
}
