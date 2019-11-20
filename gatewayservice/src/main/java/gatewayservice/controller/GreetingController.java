package gatewayservice.controller;

import gatewayservice.model.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/hotel/book")
    public ResponseEntity bookRoom(@RequestBody Room room) {

        return ResponseEntity.ok(room);
    }
}
