package com.android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface NetworkWatchlistAppResultProtoOrBuilder extends MessageLiteOrBuilder {
    String getAppDigest();

    ByteString getAppDigestBytes();

    boolean getEncodedResult();

    boolean hasAppDigest();

    boolean hasEncodedResult();
}
