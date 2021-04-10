package com.android.server.role;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface RoleUserStateProtoOrBuilder extends MessageLiteOrBuilder {
    String getPackagesHash();

    ByteString getPackagesHashBytes();

    RoleProto getRoles(int i);

    int getRolesCount();

    List<RoleProto> getRolesList();

    int getUserId();

    int getVersion();

    boolean hasPackagesHash();

    boolean hasUserId();

    boolean hasVersion();
}
