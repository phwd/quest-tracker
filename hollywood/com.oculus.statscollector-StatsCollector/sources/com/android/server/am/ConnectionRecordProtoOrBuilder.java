package com.android.server.am;

import com.android.server.am.ConnectionRecordProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ConnectionRecordProtoOrBuilder extends MessageLiteOrBuilder {
    ConnectionRecordProto.Flag getFlags(int i);

    int getFlagsCount();

    List<ConnectionRecordProto.Flag> getFlagsList();

    String getHexHash();

    ByteString getHexHashBytes();

    String getServiceName();

    ByteString getServiceNameBytes();

    int getUserId();

    boolean hasHexHash();

    boolean hasServiceName();

    boolean hasUserId();
}
