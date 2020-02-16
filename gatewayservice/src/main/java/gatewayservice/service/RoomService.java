package gatewayservice.service;

import gatewayservice.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;


@Service
@Slf4j
public class RoomService {
    private List<String> hotelNames = Collections.unmodifiableList(
            Arrays.asList(
                    "Marriott",
                    "Radisson",
                    "HolidayInn",
                    "Sputnik",
                    "ibis"));
    private ScheduledExecutorService processingPool = Executors.newScheduledThreadPool(3);

    @Autowired
    private KafkaService kafkaService;

    public void bookRoom(Room room) {
        log.info("Send room to kafka, room={}", room);
        kafkaService.sendToKafka(room);
    }

    public Room generateRandomRoom() {
        Room room = new Room();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        room.setHotelName(hotelNames.get(random.nextInt(0, hotelNames.size())));
        room.setRoomName(String.valueOf(random.nextInt(1, 10000)));
        room.setPrice(random.nextDouble(20.0,900.0));
        room.setStatus("booked");
        room.setFromDate("01.01.2020");
        room.setToDate("10.01.2020");
        return room;
    }

    public void bookRandomRoomsContinuously(long periodicDelaySeconds) {
        processingPool.scheduleAtFixedRate(()-> bookRoom(generateRandomRoom()),
                0, periodicDelaySeconds, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void onDestroy() {
        processingPool.shutdown();
    }
}
