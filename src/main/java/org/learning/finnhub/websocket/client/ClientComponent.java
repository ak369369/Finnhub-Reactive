package org.learning.finnhub.websocket.client;

import org.learning.finnhub.utils.BuildStockPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@Component
public class ClientComponent implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Value("${server.port}")
    private int serverPort;

    @Value("${sample.path}")
    private String samplePath;

    @Autowired
    private BuildStockPayload buildStockPayload;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        System.out.println(" ======buildStockPayload.getListOfPayloads().toString()======= "+ buildStockPayload.getListOfPayloads().toString());

        WebSocketClient webSocketClient = new ReactorNettyWebSocketClient();

        Client clientOne = new Client();
        clientOne.connect(webSocketClient, getURI());
        new ClientLogic().doLogic(clientOne, buildStockPayload.getListOfPayloads());


        Mono
            .delay(Duration.ofSeconds(36000))
                //.delaySubscription(longRunningMethod().subscribe())
            .publishOn(Schedulers.boundedElastic())
            .subscribe(value -> {
               clientOne.disconnect();
             //  clientTwo.disconnect();

                SpringApplication.exit(applicationContext, () -> 0);
            });
    }

    private URI getURI() {
        try {
            return new URI("wss://ws.finnhub.io?token=cr5mbfhr01qgfrnl7uogcr5mbfhr01qgfrnl7up0");
        } catch (URISyntaxException Use) {
            throw new IllegalArgumentException(Use);
        }
    }
}
