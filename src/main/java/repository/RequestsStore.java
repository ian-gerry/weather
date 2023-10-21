package repository;

import java.time.Instant;

public interface RequestsStore {

    long write(Instant instant, String verb);
}
