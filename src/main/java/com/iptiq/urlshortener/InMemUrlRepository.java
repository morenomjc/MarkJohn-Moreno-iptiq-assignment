package com.iptiq.urlshortener;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemUrlRepository implements UrlRepository
{
    private final Map<String, String> urlRepo = new ConcurrentHashMap<>();
    private final Map<String, Instant> urlDuration = new ConcurrentHashMap<>();

    @Override
    public void storeUrl(String key, String value, Instant expiration)
    {
        checkKeyDoesNotExists(key);
        urlRepo.put(key, value);
        urlDuration.put(key, expiration);
    }

    @Override
    public String retrieveUrl(String key)
    {
        checkKeyExistAndIsNotExpired(key);
        return urlRepo.get(key);
    }

    private void checkKeyDoesNotExists(String key)
    {
        if (urlRepo.containsKey(key)) {
            throw new IllegalArgumentException("Key already exists");
        }
    }

    private void checkKeyExistAndIsNotExpired(String key)
    {
        if (urlDuration.containsKey(key)) {
            Instant expiration = urlDuration.get(key);
            Instant now = Instant.now();

            if (expiration.isBefore(now)) {
                urlRepo.remove(key);
                urlDuration.remove(key);
                throw new IllegalArgumentException("Key already expired");
            }
        } else {
            throw new IllegalArgumentException("Key does not exists");
        }
    }
}
