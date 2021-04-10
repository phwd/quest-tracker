package com.android.server;

import android.app.AlarmClockInfoProto;
import android.app.AlarmManagerProto;
import android.app.PendingIntentProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface AlarmProtoOrBuilder extends MessageLiteOrBuilder {
    AlarmClockInfoProto getAlarmClock();

    int getCount();

    int getFlags();

    String getListener();

    ByteString getListenerBytes();

    PendingIntentProto getOperation();

    long getRepeatIntervalMs();

    String getTag();

    ByteString getTagBytes();

    long getTimeUntilWhenElapsedMs();

    AlarmManagerProto.AlarmType getType();

    long getWindowLengthMs();

    boolean hasAlarmClock();

    boolean hasCount();

    boolean hasFlags();

    boolean hasListener();

    boolean hasOperation();

    boolean hasRepeatIntervalMs();

    boolean hasTag();

    boolean hasTimeUntilWhenElapsedMs();

    boolean hasType();

    boolean hasWindowLengthMs();
}
