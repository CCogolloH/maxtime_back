package com.carloslogol.maxtime.back.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoConfig {

    @Value("${crypto.master-key}")
    private String masterKey;

    public String getMasterKey() {
        return masterKey;
    }

}
