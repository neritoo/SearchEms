package com.gavilan.searchems.ping;

import com.gavilan.searchems.ping.services.PingPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingAPI {

    private final PingPrinterService pingPrinterService;

    @Autowired
    public PingAPI(PingPrinterService pingPrinterService) {
        this.pingPrinterService = pingPrinterService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        pingPrinterService.printPing();
        return ResponseEntity.ok("PING");
    }

}
