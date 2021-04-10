package com.android.server.biometrics.fingerprint;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface FingerprintServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    FingerprintUserStatsProto getUsers(int i);

    int getUsersCount();

    List<FingerprintUserStatsProto> getUsersList();
}
