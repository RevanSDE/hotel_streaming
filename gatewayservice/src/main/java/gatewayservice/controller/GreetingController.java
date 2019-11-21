package gatewayservice.controller;

import gatewayservice.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GreetingController {

    @RequestMapping("/hotel/book")
    public ResponseEntity bookRoom(@RequestBody Room room) {
        log.info("Received room entity: {}", room);
        return ResponseEntity.ok(room);
    }
}
