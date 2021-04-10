package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface AppBindRecordProtoOrBuilder extends MessageLiteOrBuilder {
    String getClientProcName();

    ByteString getClientProcNameBytes();

    String getConnections(int i);

    ByteString getConnectionsBytes(int i);

    int getConnectionsCount();

    List<String> getConnectionsList();

    String getServiceName();

    ByteString getServiceNameBytes();

    boolean hasClientProcName();

    boolean hasServiceName();
}
