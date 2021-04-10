package com.android.server.job;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface GrantedUriPermissionsDumpProtoOrBuilder extends MessageLiteOrBuilder {
    int getFlags();

    String getPermissionOwner();

    ByteString getPermissionOwnerBytes();

    int getSourceUserId();

    String getTag();

    ByteString getTagBytes();

    String getUris(int i);

    ByteString getUrisBytes(int i);

    int getUrisCount();

    List<String> getUrisList();

    boolean hasFlags();

    boolean hasPermissionOwner();

    boolean hasSourceUserId();

    boolean hasTag();
}
