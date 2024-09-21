package org.learning.finnhub.websocket.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.learning.finnhub.kafka.producer.SampleProducer;
import org.learning.finnhub.websocket.response.FinnhubRecordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import static org.learning.finnhub.kafka.producer.SampleProducer.BOOTSTRAP_SERVERS;

@Component
public class ClientLogic {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final static AtomicInteger MESSAGE_ID;

    private static final ObjectMapper mapper = new ObjectMapper();

   static {
        MESSAGE_ID = new AtomicInteger(0);
    }

    SampleProducer producer = new SampleProducer(BOOTSTRAP_SERVERS);

    public void doLogic(Client client, ArrayList<String> listOfPayloads) {

        Mono
            .fromRunnable(
                () -> client.send(listOfPayloads)
            )
            .thenMany(client.receive())
            .doOnNext(
                    new Consumer<String>() {
                        @Override
                        public void accept(String message) {
                            FinnhubRecordResponse response = null;
                            try {
                                response = mapper.readValue(message, FinnhubRecordResponse.class);
                                logger.info("Client id=[{}] -> received: [{}]", client.session().map(WebSocketSession::getId).orElse(""), message);
                                //response.getData().forEach(entry -> kafkaJsonProducer.sendMessage(finalResponse));
                                System.out.println("finalResponse.toString()====> "+ response.toString());
                                producer.sendMessages("alibou",1 ,response );
                                //producer.close();
                            } catch (JsonProcessingException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }


                        }


                    }

            )
            .subscribe();


    }



}
