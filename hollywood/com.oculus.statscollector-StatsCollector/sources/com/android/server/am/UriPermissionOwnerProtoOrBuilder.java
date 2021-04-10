package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UriPermissionOwnerProtoOrBuilder extends MessageLiteOrBuilder {
    String getOwner();

    ByteString getOwnerBytes();

    GrantUriProto getReadPerms(int i);

    int getReadPermsCount();

    List<GrantUriProto> getReadPermsList();

    GrantUriProto getWritePerms(int i);

    int getWritePermsCount();

    List<GrantUriProto> getWritePermsList();

    boolean hasOwner();
}
