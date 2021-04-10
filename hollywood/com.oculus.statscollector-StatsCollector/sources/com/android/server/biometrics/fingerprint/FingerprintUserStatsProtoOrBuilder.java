package com.android.server.biometrics.fingerprint;

import com.google.protobuf.MessageLiteOrBuilder;

public interface FingerprintUserStatsProtoOrBuilder extends MessageLiteOrBuilder {
    PerformanceStatsProto getCrypto();

    PerformanceStatsProto getNormal();

    int getNumFingerprints();

    int getUserId();

    boolean hasCrypto();

    boolean hasNormal();

    boolean hasNumFingerprints();

    boolean hasUserId();
}
