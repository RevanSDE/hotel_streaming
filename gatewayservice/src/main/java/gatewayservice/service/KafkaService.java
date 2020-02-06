package gatewayservice.service;

import gatewayservice.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaService {

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Room> kafkaRoomTemplate;

    public void sendToKafka(Room room) {
        kafkaRoomTemplate.send(topic, room);
    }
}
