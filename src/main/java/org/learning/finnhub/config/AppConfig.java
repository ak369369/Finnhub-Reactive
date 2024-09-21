package org.learning.finnhub.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
//@AllArgsConstructor
public class AppConfig {

    private final Spring spring;

    private final Config config;

    private final Logging logging;

    private final Server server;

    private final Sample sample;

    @Autowired
    public AppConfig(Spring spring, Config config, Logging logging, Server server, Sample sample) {
        this.spring = spring;
        this.config = config;
        this.logging = logging;
        this.server = server;
        this.sample = sample;
    }

}
