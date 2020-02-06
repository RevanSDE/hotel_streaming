package gatewayservice.controller;

import gatewayservice.model.Room;
import gatewayservice.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/hotel/book")
    public ResponseEntity bookRoom(@RequestBody Room room) {
        log.info("Received room entity: {}", room);
        roomService.bookRoom(room);
        return ResponseEntity.ok(room);
    }

    @RequestMapping("/hotel/test_booking")
    public ResponseEntity testRoomBooking() {
        Room room = roomService.generateRandomRoom();
        log.info("Generated random room: {}", room);
        roomService.bookRoom(room);
        return ResponseEntity.ok(room);
    }

}
