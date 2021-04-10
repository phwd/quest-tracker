package com.android.server.am;

import com.google.protobuf.MessageLiteOrBuilder;

public interface ActivityManagerServiceProtoOrBuilder extends MessageLiteOrBuilder {
    ActivityManagerServiceDumpActivitiesProto getActivities();

    ActivityManagerServiceDumpBroadcastsProto getBroadcasts();

    ActivityManagerServiceDumpProcessesProto getProcesses();

    ActivityManagerServiceDumpServicesProto getServices();

    boolean hasActivities();

    boolean hasBroadcasts();

    boolean hasProcesses();

    boolean hasServices();
}
