package com.android.server.am;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NeededUriGrantsProtoOrBuilder extends MessageLiteOrBuilder {
    int getFlags();

    GrantUriProto getGrants(int i);

    int getGrantsCount();

    List<GrantUriProto> getGrantsList();

    String getTargetPackage();

    ByteString getTargetPackageBytes();

    int getTargetUid();

    boolean hasFlags();

    boolean hasTargetPackage();

    boolean hasTargetUid();
}
