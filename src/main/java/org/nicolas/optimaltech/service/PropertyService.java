package org.nicolas.optimaltech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertyService {

    @Autowired
    private Environment env;

    public String getProperty(String key) {
        return env.getProperty(key);
    }
}
