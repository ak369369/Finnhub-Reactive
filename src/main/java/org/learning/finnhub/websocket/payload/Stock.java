package org.learning.finnhub.websocket.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Stock {

    private String symbol;
    private String type;

}
