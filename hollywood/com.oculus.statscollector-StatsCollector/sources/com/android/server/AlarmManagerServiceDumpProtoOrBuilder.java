package com.android.server;

import com.android.internal.util.LocalLogProto;
import com.android.server.AlarmManagerServiceDumpProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface AlarmManagerServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    AlarmManagerServiceDumpProto.AlarmStat getAlarmStats(int i);

    int getAlarmStatsCount();

    List<AlarmManagerServiceDumpProto.AlarmStat> getAlarmStatsList();

    IdleDispatchEntryProto getAllowWhileIdleDispatches(int i);

    int getAllowWhileIdleDispatchesCount();

    List<IdleDispatchEntryProto> getAllowWhileIdleDispatchesList();

    int getBroadcastRefCount();

    long getCurrentTime();

    int getDelayedAlarmCount();

    int getDeviceIdleUserWhitelistAppIds(int i);

    int getDeviceIdleUserWhitelistAppIdsCount();

    List<Integer> getDeviceIdleUserWhitelistAppIdsList();

    long getElapsedRealtime();

    ForceAppStandbyTrackerProto getForceAppStandbyTracker();

    boolean getIsInteractive();

    AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatch getLastAllowWhileIdleDispatchTimes(int i);

    int getLastAllowWhileIdleDispatchTimesCount();

    List<AlarmManagerServiceDumpProto.LastAllowWhileIdleDispatch> getLastAllowWhileIdleDispatchTimesList();

    long getLastTimeChangeClockTime();

    long getLastTimeChangeRealtime();

    int getListenerFinishCount();

    int getListenerSendCount();

    long getMaxDelayDurationMs();

    long getMaxNonInteractiveDurationMs();

    long getMaxWakeupDelayMs();

    AlarmClockMetadataProto getNextAlarmClockMetadata(int i);

    int getNextAlarmClockMetadataCount();

    List<AlarmClockMetadataProto> getNextAlarmClockMetadataList();

    AlarmProto getNextWakeFromIdle();

    InFlightProto getOutstandingDeliveries(int i);

    int getOutstandingDeliveriesCount();

    List<InFlightProto> getOutstandingDeliveriesList();

    AlarmProto getPastDueNonWakeupAlarms(int i);

    int getPastDueNonWakeupAlarmsCount();

    List<AlarmProto> getPastDueNonWakeupAlarmsList();

    BatchProto getPendingAlarmBatches(int i);

    int getPendingAlarmBatchesCount();

    List<BatchProto> getPendingAlarmBatchesList();

    AlarmProto getPendingIdleUntil();

    int getPendingIntentFinishCount();

    int getPendingIntentSendCount();

    AlarmProto getPendingUserBlockedBackgroundAlarms(int i);

    int getPendingUserBlockedBackgroundAlarmsCount();

    List<AlarmProto> getPendingUserBlockedBackgroundAlarmsList();

    AlarmProto getPendingWhileIdleAlarms(int i);

    int getPendingWhileIdleAlarmsCount();

    List<AlarmProto> getPendingWhileIdleAlarmsList();

    LocalLogProto getRecentProblems();

    WakeupEventProto getRecentWakeupHistory(int i);

    int getRecentWakeupHistoryCount();

    List<WakeupEventProto> getRecentWakeupHistoryList();

    ConstantsProto getSettings();

    long getTimeChangeEventCount();

    long getTimeSinceLastDispatchMs();

    long getTimeSinceLastWakeupMs();

    long getTimeSinceLastWakeupSetMs();

    long getTimeSinceNonInteractiveMs();

    long getTimeUntilNextNonWakeupAlarmMs();

    long getTimeUntilNextNonWakeupDeliveryMs();

    long getTimeUntilNextWakeupMs();

    AlarmManagerServiceDumpProto.TopAlarm getTopAlarms(int i);

    int getTopAlarmsCount();

    List<AlarmManagerServiceDumpProto.TopAlarm> getTopAlarmsList();

    long getTotalDelayTimeMs();

    int getUseAllowWhileIdleShortTime(int i);

    int getUseAllowWhileIdleShortTimeCount();

    List<Integer> getUseAllowWhileIdleShortTimeList();

    boolean hasBroadcastRefCount();

    boolean hasCurrentTime();

    boolean hasDelayedAlarmCount();

    boolean hasElapsedRealtime();

    boolean hasForceAppStandbyTracker();

    boolean hasIsInteractive();

    boolean hasLastTimeChangeClockTime();

    boolean hasLastTimeChangeRealtime();

    boolean hasListenerFinishCount();

    boolean hasListenerSendCount();

    boolean hasMaxDelayDurationMs();

    boolean hasMaxNonInteractiveDurationMs();

    boolean hasMaxWakeupDelayMs();

    boolean hasNextWakeFromIdle();

    boolean hasPendingIdleUntil();

    boolean hasPendingIntentFinishCount();

    boolean hasPendingIntentSendCount();

    boolean hasRecentProblems();

    boolean hasSettings();

    boolean hasTimeChangeEventCount();

    boolean hasTimeSinceLastDispatchMs();

    boolean hasTimeSinceLastWakeupMs();

    boolean hasTimeSinceLastWakeupSetMs();

    boolean hasTimeSinceNonInteractiveMs();

    boolean hasTimeUntilNextNonWakeupAlarmMs();

    boolean hasTimeUntilNextNonWakeupDeliveryMs();

    boolean hasTimeUntilNextWakeupMs();

    boolean hasTotalDelayTimeMs();
}
