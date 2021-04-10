package com.android.server.am;

import android.content.IntentFilterProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BroadcastFilterProtoOrBuilder extends MessageLiteOrBuilder {
    String getHexHash();

    ByteString getHexHashBytes();

    IntentFilterProto getIntentFilter();

    int getOwningUserId();

    String getRequiredPermission();

    ByteString getRequiredPermissionBytes();

    boolean hasHexHash();

    boolean hasIntentFilter();

    boolean hasOwningUserId();

    boolean hasRequiredPermission();
}
