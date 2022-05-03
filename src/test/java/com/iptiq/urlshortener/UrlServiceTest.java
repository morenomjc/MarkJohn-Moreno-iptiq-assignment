package com.iptiq.urlshortener;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class UrlServiceTest
{
    private final KeyService keyService = new RandomKeyService();
    private final UrlRepository urlRepository = new InMemUrlRepository();

    private final UrlService urlService = new UrlServiceImpl(keyService, urlRepository);

    private final String domain = "https://www.iptiq.com";

    @Test
    void shortenCustom_whenSaved_ShouldBeRetrievable()
    {
        Duration duration = Duration.ofDays(30);
        String originalUrl = "https://careers.swissre.com/job/Hoofddorp-Backend-Developer/763847901/";
        String shortUrl = urlService.shortenCustom(originalUrl, "backend-dev", domain, duration);
        System.out.println(shortUrl);
        String savedUrl = urlService.findUrl(shortUrl);
        System.out.println(savedUrl);
        assertEquals(savedUrl, originalUrl);

        assertThrowsExactly(IllegalArgumentException.class, () ->
                urlService.shortenCustom(originalUrl, "backend-dev", domain, duration), "Key already exists");
    }

    @Test
    void shortenRandom_whenSaved_ShouldBeRetrievable()
    {
        Duration duration = Duration.ofDays(30);
        String originalUrl = "https://careers.swissre.com/job/Hoofddorp-Backend-Developer/763847901/";
        String shortUrl = urlService.shortenRandom(originalUrl, domain, duration);
        System.out.println(shortUrl);
        String savedUrl = urlService.findUrl(shortUrl);
        System.out.println(savedUrl);
        assertEquals(savedUrl, originalUrl);
    }

    @Test
    void findUrl_whenExpired_ShouldThrowExceptionAndKeyRemoved()
    {
        Duration duration = Duration.ofNanos(1);
        String originalUrl = "https://careers.swissre.com/job/Hoofddorp-Backend-Developer/763847901/";
        String shortUrl = urlService.shortenRandom(originalUrl, domain, duration);

        assertThrowsExactly(IllegalArgumentException.class, () -> urlService.findUrl(shortUrl), "Key already expired");
        assertThrowsExactly(IllegalArgumentException.class, () -> urlService.findUrl(shortUrl), "Key does not exists");
    }
}
