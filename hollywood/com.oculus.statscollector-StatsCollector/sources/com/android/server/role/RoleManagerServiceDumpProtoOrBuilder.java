package com.android.server.role;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface RoleManagerServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    RoleUserStateProto getUserStates(int i);

    int getUserStatesCount();

    List<RoleUserStateProto> getUserStatesList();
}
