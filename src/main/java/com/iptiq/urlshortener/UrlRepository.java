package com.iptiq.urlshortener;

import java.time.Instant;

public interface UrlRepository
{
    void storeUrl(String key, String value, Instant expiration);
    String retrieveUrl(String key);
}
