package com.android.server.am;

import com.google.protobuf.MessageLiteOrBuilder;

public interface ActivityManagerServiceDumpActivitiesProtoOrBuilder extends MessageLiteOrBuilder {
    ActivityStackSupervisorProto getActivityStackSupervisor();

    boolean hasActivityStackSupervisor();
}
