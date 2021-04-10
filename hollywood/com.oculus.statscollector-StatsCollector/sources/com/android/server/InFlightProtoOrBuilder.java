package com.android.server;

import android.app.AlarmManagerProto;
import android.app.PendingIntentProto;
import android.os.WorkSourceProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface InFlightProtoOrBuilder extends MessageLiteOrBuilder {
    AlarmManagerProto.AlarmType getAlarmType();

    BroadcastStatsProto getBroadcastStats();

    FilterStatsProto getFilterStats();

    PendingIntentProto getPendingIntent();

    String getTag();

    ByteString getTagBytes();

    int getUid();

    long getWhenElapsedMs();

    WorkSourceProto getWorkSource();

    boolean hasAlarmType();

    boolean hasBroadcastStats();

    boolean hasFilterStats();

    boolean hasPendingIntent();

    boolean hasTag();

    boolean hasUid();

    boolean hasWhenElapsedMs();

    boolean hasWorkSource();
}
