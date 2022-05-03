package com.iptiq.urlshortener;

import java.time.Duration;
import java.time.Instant;

public class UrlServiceImpl implements UrlService
{

    private final KeyService keyService;
    private final UrlRepository urlRepository;

    public UrlServiceImpl(KeyService keyService, UrlRepository urlRepository)
    {
        this.keyService = keyService;
        this.urlRepository = urlRepository;
    }

    @Override
    public String shortenCustom(String originalUrl, String key, String domain, Duration ttl)
    {
        String shortUrl = shortenUrl(domain, key);
        Instant expiration = Instant.now().plusMillis(ttl.toMillis());
        urlRepository.storeUrl(shortUrl, originalUrl, expiration);
        return shortUrl;
    }

    @Override
    public String shortenRandom(String originalUrl, String domain, Duration ttl)
    {
        String key = keyService.generateKey(6);
        return shortenCustom(originalUrl, key, domain, ttl);
    }

    @Override
    public String findUrl(String key)
    {
        return urlRepository.retrieveUrl(key);
    }

    private String shortenUrl(String domain, String key)
    {
        if (!domain.endsWith("/"))
            domain = domain.concat("/");

        return domain.concat(key);
    }
}
