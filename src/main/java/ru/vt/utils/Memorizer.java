package ru.vt.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memorizer<T, U> {
    private final Map<T, U> cache = new ConcurrentHashMap<>();

    private Function<T, U> doMemorize(Function<T, U> function) {
        return input -> cache.computeIfAbsent(input, function);
    }

    public static <T, U> Function<T, U> memorize(Function<T, U> function) {
        return new Memorizer<T, U>().doMemorize(function);
    }
}