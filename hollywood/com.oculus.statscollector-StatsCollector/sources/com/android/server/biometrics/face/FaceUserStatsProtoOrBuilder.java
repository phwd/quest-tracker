package com.android.server.biometrics.face;

import com.google.protobuf.MessageLiteOrBuilder;

public interface FaceUserStatsProtoOrBuilder extends MessageLiteOrBuilder {
    FaceActionStatsProto getCrypto();

    FaceActionStatsProto getNormal();

    int getNumFaces();

    int getUserId();

    boolean hasCrypto();

    boolean hasNormal();

    boolean hasNumFaces();

    boolean hasUserId();
}
