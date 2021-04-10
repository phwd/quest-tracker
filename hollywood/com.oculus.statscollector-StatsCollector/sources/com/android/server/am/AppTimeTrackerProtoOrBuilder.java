package com.android.server.am;

import android.util.Duration;
import com.android.server.am.AppTimeTrackerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface AppTimeTrackerProtoOrBuilder extends MessageLiteOrBuilder {
    AppTimeTrackerProto.PackageTime getPackageTimes(int i);

    int getPackageTimesCount();

    List<AppTimeTrackerProto.PackageTime> getPackageTimesList();

    String getReceiver();

    ByteString getReceiverBytes();

    String getStartedPackage();

    ByteString getStartedPackageBytes();

    Duration getStartedTime();

    long getTotalDurationMs();

    boolean hasReceiver();

    boolean hasStartedPackage();

    boolean hasStartedTime();

    boolean hasTotalDurationMs();
}
