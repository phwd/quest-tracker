package com.android.server.connectivity;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CellularDataOrBuilder extends MessageLiteOrBuilder {
    boolean getIsRoaming();

    String getNetworkMccmnc();

    ByteString getNetworkMccmncBytes();

    RadioTech getRatType();

    int getSignalStrength();

    String getSimMccmnc();

    ByteString getSimMccmncBytes();

    boolean hasIsRoaming();

    boolean hasNetworkMccmnc();

    boolean hasRatType();

    boolean hasSignalStrength();

    boolean hasSimMccmnc();
}
