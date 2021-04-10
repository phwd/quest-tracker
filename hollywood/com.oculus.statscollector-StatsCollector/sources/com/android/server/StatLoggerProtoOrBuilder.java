package com.android.server;

import com.android.server.StatLoggerProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface StatLoggerProtoOrBuilder extends MessageLiteOrBuilder {
    StatLoggerProto.Event getEvents(int i);

    int getEventsCount();

    List<StatLoggerProto.Event> getEventsList();
}
