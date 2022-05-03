package com.iptiq.urlshortener;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomKeyServiceTest
{

    private final KeyService keyService = new RandomKeyService();

    @Test
    void generateKey_returnsStringOfSpecifiedLength()
    {
        String key = keyService.generateKey(6);

        assertEquals(key.length(), 6);
    }
}
