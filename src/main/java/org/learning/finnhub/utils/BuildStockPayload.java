package org.learning.finnhub.utils;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.learning.finnhub.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@Configuration
@Component
public class BuildStockPayload {

    private final AppConfig appConfig;
    private final Environment environment;

    public ArrayList<String> listOfPayloads = new ArrayList<>();

    @Autowired
    public BuildStockPayload(AppConfig appConfig, Environment environment) {
        this.appConfig = appConfig;
        this.environment = environment;
    }

    @PostConstruct
    public void init() {

        final String str = appConfig.getConfig().getPayload();

        String[] arr = appConfig.getConfig().getStocks();

        System.out.println("======STOCKS========= " + Arrays.toString(appConfig.getConfig().getStocks()));

        for (String s : arr) {
            String s1 = str.replace("STOCK", s);
            listOfPayloads.add(s1);
        }

        };
    }
