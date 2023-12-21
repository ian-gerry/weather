package com.x.repository;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileBasedRequestStoreAdapter implements RequestsStorePort {

    private final String FILENAME = "requests.csv";
    @Override
    public long write(Instant instant, String verb, String ip, String uri){
        Path p = Path.of("","requests.csv");
        return write(p,instant,verb,ip,uri);
    }

    protected long write(Path p, Instant instant, String verb, String ip, String uri){

        try {
            String ln = String.format("%d,%s,%s,%s",instant.getEpochSecond(),verb,ip,uri);
            Files.writeString(p, ln,StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }catch(IOException ex){
            throw new RuntimeException("Failed trying to record request",ex);
        }
        return 0;
    }

    public List<String> retrieve(String ip, int max) throws IOException {
        Path p = Path.of("","requests.csv");
        return Files.lines(p)
                .limit(max)
                .filter(s -> {
                       String[] arr = s.split(",");
                       String fip = arr[1];
                       return ip.equals(fip);
                })
                .collect(Collectors.toList());
    }
}
