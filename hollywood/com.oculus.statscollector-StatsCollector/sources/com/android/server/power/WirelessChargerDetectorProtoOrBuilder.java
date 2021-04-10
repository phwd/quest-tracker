package com.android.server.power;

import com.android.server.power.WirelessChargerDetectorProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WirelessChargerDetectorProtoOrBuilder extends MessageLiteOrBuilder {
    long getDetectionStartTimeMs();

    WirelessChargerDetectorProto.VectorProto getFirstSample();

    boolean getIsAtRest();

    boolean getIsDetectionInProgress();

    boolean getIsMustUpdateRestPosition();

    boolean getIsPoweredWirelessly();

    WirelessChargerDetectorProto.VectorProto getLastSample();

    int getMovingSamples();

    WirelessChargerDetectorProto.VectorProto getRest();

    int getTotalSamples();

    boolean hasDetectionStartTimeMs();

    boolean hasFirstSample();

    boolean hasIsAtRest();

    boolean hasIsDetectionInProgress();

    boolean hasIsMustUpdateRestPosition();

    boolean hasIsPoweredWirelessly();

    boolean hasLastSample();

    boolean hasMovingSamples();

    boolean hasRest();

    boolean hasTotalSamples();
}
