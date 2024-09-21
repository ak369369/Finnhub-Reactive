package org.learning.finnhub.websocket.response;

import java.util.List;

public record FinnhubRecordResponse(
        List<EntryRecord> data,
        String type)
{

}
