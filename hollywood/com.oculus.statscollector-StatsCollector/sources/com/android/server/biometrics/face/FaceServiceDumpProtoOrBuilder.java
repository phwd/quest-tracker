package com.android.server.biometrics.face;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface FaceServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    FaceUserStatsProto getUsers(int i);

    int getUsersCount();

    List<FaceUserStatsProto> getUsersList();
}
