package com.android.server.biometrics.fingerprint;

import com.google.protobuf.MessageLiteOrBuilder;

public interface PerformanceStatsProtoOrBuilder extends MessageLiteOrBuilder {
    int getAccept();

    int getAcquire();

    int getLockout();

    int getPermanentLockout();

    int getReject();

    boolean hasAccept();

    boolean hasAcquire();

    boolean hasLockout();

    boolean hasPermanentLockout();

    boolean hasReject();
}
