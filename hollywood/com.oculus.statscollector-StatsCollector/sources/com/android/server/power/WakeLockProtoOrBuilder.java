package com.android.server.power;

import android.os.WakeLockLevelEnum;
import android.os.WorkSourceProto;
import com.android.server.power.WakeLockProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WakeLockProtoOrBuilder extends MessageLiteOrBuilder {
    long getAcqMs();

    WakeLockProto.WakeLockFlagsProto getFlags();

    boolean getIsDisabled();

    boolean getIsNotifiedLong();

    WakeLockLevelEnum getLockLevel();

    int getPid();

    String getTag();

    ByteString getTagBytes();

    int getUid();

    WorkSourceProto getWorkSource();

    boolean hasAcqMs();

    boolean hasFlags();

    boolean hasIsDisabled();

    boolean hasIsNotifiedLong();

    boolean hasLockLevel();

    boolean hasPid();

    boolean hasTag();

    boolean hasUid();

    boolean hasWorkSource();
}
