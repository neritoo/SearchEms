package com.gavilan.searchems.ping.services.impl;

import com.gavilan.searchems.ping.services.PingPrinterService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ConsolePingPrinter implements PingPrinterService {

    @Override
    public void printPing() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts + " - PING");
    }
}
