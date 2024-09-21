package org.learning.finnhub.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
public class Kafka {
    private Consumer consumer;
    private Producer producer;
    private Listener listener;
    private Main main;

}
