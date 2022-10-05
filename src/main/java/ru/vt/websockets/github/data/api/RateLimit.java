package ru.vt.websockets.github.data.api;

import lombok.Data;

@Data
public class RateLimit {
    private Rate rate;
    public record Rate(int limit, int used, int remaining, int reset) { }
}
