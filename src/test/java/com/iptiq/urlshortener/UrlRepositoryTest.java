package com.iptiq.urlshortener;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UrlRepositoryTest
{

    private final UrlRepository urlRepository = new InMemUrlRepository();

    @Test
    void storeUrl_whenSaved_ShouldBeRetrievable()
    {
        String key = "key1";
        String value = "val1";
        Instant expiration = Instant.now().plusSeconds(100);

        urlRepository.storeUrl(key, value, expiration);

        String url = urlRepository.retrieveUrl(key);
        assertEquals(value, url);
    }

    @Test
    void storeUrl_checksIfKeyExists()
    {
        String key = "key2";
        String value = "val2";
        Instant expiration = Instant.now().plusSeconds(100);

        urlRepository.storeUrl(key, value, expiration);

        assertThrows(IllegalArgumentException.class,
                () -> urlRepository.storeUrl(key, value, expiration), "Key already exists");
    }

    @Test
    void retrieveUrl_checksIfExpired()
    {
        String key = "key3";
        String value = "val3";
        Instant expiration = Instant.now().plusNanos(1);

        urlRepository.storeUrl(key, value, expiration);

        assertThrows(IllegalArgumentException.class,
                () -> urlRepository.retrieveUrl(key), "Key already expired");
    }

    @Test
    void retrieveUrl_returnsWhenNotExpired()
    {
        String key = "key4";
        String value = "val4";
        Instant expiration = Instant.now().plusSeconds(10);

        urlRepository.storeUrl(key, value, expiration);

        String url = urlRepository.retrieveUrl(key);
        assertEquals(value, url);
    }
}
