package com.x.repository;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class FileBasedRequestStorePort implements RequestsStorePort {

    private final String FILENAME = "requests.csv";
    @Override
    public long write(Instant instant, String verb) {
        return 0;
    }
}
