package com.x.repository;

import java.time.Instant;

public interface RequestsStorePort {

    long write(Instant instant, String verb);
}
