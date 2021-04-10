package com.android.server.connectivity;

import com.google.protobuf.MessageLiteOrBuilder;

public interface WifiDataOrBuilder extends MessageLiteOrBuilder {
    int getSignalStrength();

    ApBand getWifiBand();

    boolean hasSignalStrength();

    boolean hasWifiBand();
}
