package com.iptiq.urlshortener;

import java.util.UUID;

public class RandomKeyService implements KeyService
{
    @Override
    public String generateKey(int length)
    {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, length);
    }
}
