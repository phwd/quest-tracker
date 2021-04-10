package com.android.server.job;

import com.android.server.job.ConstantsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ConstantsProtoOrBuilder extends MessageLiteOrBuilder {
    int getBgCriticalJobCount();

    int getBgLowJobCount();

    int getBgModerateJobCount();

    int getBgNormalJobCount();

    double getConnCongestionDelayFrac();

    double getConnPrefetchRelaxFrac();

    int getFgJobCount();

    double getHeavyUseFactor();

    MaxJobCountsPerMemoryTrimLevelProto getMaxJobCountsScreenOff();

    MaxJobCountsPerMemoryTrimLevelProto getMaxJobCountsScreenOn();

    int getMaxStandardRescheduleCount();

    int getMaxWorkRescheduleCount();

    int getMinBatteryNotLowCount();

    int getMinChargingCount();

    int getMinConnectivityCount();

    int getMinContentCount();

    long getMinExpBackoffTimeMs();

    int getMinIdleCount();

    long getMinLinearBackoffTimeMs();

    int getMinReadyJobsCount();

    int getMinStorageNotLowCount();

    double getModerateUseFactor();

    ConstantsProto.QuotaController getQuotaController();

    int getScreenOffJobConcurrencyIncreaseDelayMs();

    int getStandbyBeats(int i);

    int getStandbyBeatsCount();

    List<Integer> getStandbyBeatsList();

    long getStandbyHeartbeatTimeMs();

    ConstantsProto.TimeController getTimeController();

    boolean getUseHeartbeats();

    boolean hasBgCriticalJobCount();

    boolean hasBgLowJobCount();

    boolean hasBgModerateJobCount();

    boolean hasBgNormalJobCount();

    boolean hasConnCongestionDelayFrac();

    boolean hasConnPrefetchRelaxFrac();

    boolean hasFgJobCount();

    boolean hasHeavyUseFactor();

    boolean hasMaxJobCountsScreenOff();

    boolean hasMaxJobCountsScreenOn();

    boolean hasMaxStandardRescheduleCount();

    boolean hasMaxWorkRescheduleCount();

    boolean hasMinBatteryNotLowCount();

    boolean hasMinChargingCount();

    boolean hasMinConnectivityCount();

    boolean hasMinContentCount();

    boolean hasMinExpBackoffTimeMs();

    boolean hasMinIdleCount();

    boolean hasMinLinearBackoffTimeMs();

    boolean hasMinReadyJobsCount();

    boolean hasMinStorageNotLowCount();

    boolean hasModerateUseFactor();

    boolean hasQuotaController();

    boolean hasScreenOffJobConcurrencyIncreaseDelayMs();

    boolean hasStandbyHeartbeatTimeMs();

    boolean hasTimeController();

    boolean hasUseHeartbeats();
}
