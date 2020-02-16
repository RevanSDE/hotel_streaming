package rateservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rateservice.service.RateService;

@Slf4j
@RestController
public class RateController {

    @Autowired
    private RateService rateService;

    @RequestMapping("/rate/all")
    public ResponseEntity bookRoom() {
        log.info("Send all rates");
        return ResponseEntity.ok(rateService.getStaticRates());
    }


}
