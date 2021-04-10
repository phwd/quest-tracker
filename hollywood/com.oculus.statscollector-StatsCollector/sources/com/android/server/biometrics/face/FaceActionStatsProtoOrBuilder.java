package com.android.server.biometrics.face;

import com.google.protobuf.MessageLiteOrBuilder;

public interface FaceActionStatsProtoOrBuilder extends MessageLiteOrBuilder {
    int getAccept();

    int getAcquire();

    int getLockout();

    int getLockoutPermanent();

    int getReject();

    boolean hasAccept();

    boolean hasAcquire();

    boolean hasLockout();

    boolean hasLockoutPermanent();

    boolean hasReject();
}
