package com.android.server.role;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface RoleProtoOrBuilder extends MessageLiteOrBuilder {
    String getHolders(int i);

    ByteString getHoldersBytes(int i);

    int getHoldersCount();

    List<String> getHoldersList();

    String getName();

    ByteString getNameBytes();

    boolean hasName();
}
