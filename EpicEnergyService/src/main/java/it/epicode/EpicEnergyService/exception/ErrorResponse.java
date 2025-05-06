package it.epicode.EpicEnergyService.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final int status;
    private final String messaggio;
    private final LocalDateTime timestamp;

    public ErrorResponse(int status, String messaggio, LocalDateTime timestamp) {
        this.status = status;
        this.messaggio = messaggio;
        this.timestamp = timestamp;
    }

}
