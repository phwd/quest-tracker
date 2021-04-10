package com.android.server.usage;

import com.android.server.usage.IntervalStatsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface IntervalStatsProtoOrBuilder extends MessageLiteOrBuilder {
    IntervalStatsProto.Configuration getConfigurations(int i);

    int getConfigurationsCount();

    List<IntervalStatsProto.Configuration> getConfigurationsList();

    long getEndTimeMs();

    IntervalStatsProto.Event getEventLog(int i);

    int getEventLogCount();

    List<IntervalStatsProto.Event> getEventLogList();

    IntervalStatsProto.CountAndTime getInteractive();

    IntervalStatsProto.CountAndTime getKeyguardHidden();

    IntervalStatsProto.CountAndTime getKeyguardShown();

    int getMajorVersion();

    int getMinorVersion();

    IntervalStatsProto.CountAndTime getNonInteractive();

    IntervalStatsProto.UsageStats getPackages(int i);

    int getPackagesCount();

    List<IntervalStatsProto.UsageStats> getPackagesList();

    IntervalStatsProto.StringPool getStringpool();

    boolean hasEndTimeMs();

    boolean hasInteractive();

    boolean hasKeyguardHidden();

    boolean hasKeyguardShown();

    boolean hasMajorVersion();

    boolean hasMinorVersion();

    boolean hasNonInteractive();

    boolean hasStringpool();
}
