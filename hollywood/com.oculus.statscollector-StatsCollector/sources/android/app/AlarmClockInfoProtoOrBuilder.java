package android.app;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AlarmClockInfoProtoOrBuilder extends MessageLiteOrBuilder {
    PendingIntentProto getShowIntent();

    long getTriggerTimeMs();

    boolean hasShowIntent();

    boolean hasTriggerTimeMs();
}
