package com.x.repository;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

class FileBasedRequestStoreAdapterTest {

    private FileBasedRequestStoreAdapter adapter;

    @BeforeEach
    public void setUp(){
        adapter = new FileBasedRequestStoreAdapter();
    }
        @Test
        @DisplayName("Write a request to a CSV file")
        void testWriteRequest(@TempDir Path tempDir) throws IOException {

            Instant instant = Instant.parse("2021-12-21T09:23:54Z");
            String verb = "GET";
            String ip = "127.0.0.1";
            String uri = "/index.html";
            List<String> expectedLines = List.of(instant.getEpochSecond()+",GET,127.0.0.1,/index.html");

            Path file = Paths
                    .get(tempDir + File.separator+ "requests.csv");
            Files.createFile(file);
            // Act
            assertTrue(Files.exists(file));
            adapter.write(file,instant, verb, ip, uri);

            // Assert
            Assertions.assertAll(
                    () -> Assertions.assertDoesNotThrow(() -> adapter.write(instant, verb, ip, uri)),
                    () -> Assertions.assertLinesMatch(expectedLines,
                            Files.readAllLines(tempDir.resolve("requests.csv")))
            );
        }
}