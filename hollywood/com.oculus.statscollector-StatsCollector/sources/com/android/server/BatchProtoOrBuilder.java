package com.android.server;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface BatchProtoOrBuilder extends MessageLiteOrBuilder {
    AlarmProto getAlarms(int i);

    int getAlarmsCount();

    List<AlarmProto> getAlarmsList();

    long getEndRealtime();

    int getFlags();

    long getStartRealtime();

    boolean hasEndRealtime();

    boolean hasFlags();

    boolean hasStartRealtime();
}
