package org.learning.finnhub;

import org.learning.finnhub.kafka.producer.SampleProducer;
import org.learning.finnhub.utils.BuildStockPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.learning.finnhub.kafka.producer.SampleProducer.BOOTSTRAP_SERVERS;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@SpringBootApplication
public class FinnhubApplication {

    public static void main(String[] args) throws InterruptedException, IOException {

        ApplicationContext applicationContext =  SpringApplication.run(FinnhubApplication.class, args);


       /* for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println("===============>> " + name);
        }*/

    }

}
