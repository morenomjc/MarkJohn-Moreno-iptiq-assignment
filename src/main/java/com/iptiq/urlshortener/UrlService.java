package com.iptiq.urlshortener;

import java.time.Duration;

public interface UrlService
{
    String shortenCustom(String originalUrl, String key, String domain, Duration ttl);
    String shortenRandom(String originalUrl, String domain, Duration ttl);
    String findUrl(String key);
}
