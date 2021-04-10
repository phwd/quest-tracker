package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ReceiverListProtoOrBuilder extends MessageLiteOrBuilder {
    ProcessRecordProto getApp();

    BroadcastRecordProto getCurrent();

    BroadcastFilterProto getFilters(int i);

    int getFiltersCount();

    List<BroadcastFilterProto> getFiltersList();

    String getHexHash();

    ByteString getHexHashBytes();

    boolean getLinkedToDeath();

    int getPid();

    int getUid();

    int getUser();

    boolean hasApp();

    boolean hasCurrent();

    boolean hasHexHash();

    boolean hasLinkedToDeath();

    boolean hasPid();

    boolean hasUid();

    boolean hasUser();
}
