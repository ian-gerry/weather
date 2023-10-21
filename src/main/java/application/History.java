package application;


import java.time.Instant;


public record History(Instant datetime, String request) {

}
