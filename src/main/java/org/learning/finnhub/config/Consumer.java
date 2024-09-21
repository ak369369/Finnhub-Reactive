package org.learning.finnhub.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class Consumer {
    private String bootstrapServers;
    private String groupID;
    private String autoOffsetReset;
    private String keyDeserializer;
    private String valueDeserializer;
    private Properties properties;

}
