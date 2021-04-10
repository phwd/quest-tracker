package com.android.server;

import com.google.protobuf.MessageLiteOrBuilder;

public interface ConstantsProtoOrBuilder extends MessageLiteOrBuilder {
    long getAllowWhileIdleLongDurationMs();

    long getAllowWhileIdleShortDurationMs();

    long getAllowWhileIdleWhitelistDurationMs();

    long getListenerTimeoutDurationMs();

    long getMaxIntervalDurationMs();

    long getMinFuturityDurationMs();

    long getMinIntervalDurationMs();

    boolean hasAllowWhileIdleLongDurationMs();

    boolean hasAllowWhileIdleShortDurationMs();

    boolean hasAllowWhileIdleWhitelistDurationMs();

    boolean hasListenerTimeoutDurationMs();

    boolean hasMaxIntervalDurationMs();

    boolean hasMinFuturityDurationMs();

    boolean hasMinIntervalDurationMs();
}
