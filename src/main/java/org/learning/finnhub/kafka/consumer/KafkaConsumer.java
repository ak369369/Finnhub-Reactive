package org.learning.finnhub.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.learning.finnhub.websocket.response.FinnhubRecordResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//import org.springframework.kafka.support.serializer.

import static java.lang.String.format;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "alibou", groupId = "myGroup")
    public void consumeJsonMsg(FinnhubRecordResponse message) {
        log.info(format("Consuming the message from alibou Topic:: %s", message.toString()));
    }
}
