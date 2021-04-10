package com.android.server.am;

import android.content.ConfigurationProto;
import android.os.PowerManagerProto;
import android.util.Duration;
import com.android.server.am.ActivityManagerServiceDumpProcessesProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ActivityManagerServiceDumpProcessesProtoOrBuilder extends MessageLiteOrBuilder {
    ActiveInstrumentationProto getActiveInstrumentations(int i);

    int getActiveInstrumentationsCount();

    List<ActiveInstrumentationProto> getActiveInstrumentationsList();

    UidRecordProto getActiveUids(int i);

    int getActiveUidsCount();

    List<UidRecordProto> getActiveUidsList();

    int getAdjSeq();

    boolean getAllowLowerMemLevel();

    boolean getAlwaysFinishActivities();

    AppErrorsProto getAppErrors();

    boolean getBootAnimationComplete();

    boolean getBooted();

    boolean getBooting();

    boolean getCallFinishBooting();

    boolean getConfigWillChange();

    ActivityManagerServiceDumpProcessesProto.Controller getController();

    AppTimeTrackerProto getCurrentTracker();

    ActivityManagerServiceDumpProcessesProto.DebugApp getDebug();

    int getDeviceIdleTempWhitelist(int i);

    int getDeviceIdleTempWhitelistCount();

    List<Integer> getDeviceIdleTempWhitelistList();

    int getDeviceIdleWhitelist(int i);

    int getDeviceIdleWhitelistCount();

    List<Integer> getDeviceIdleWhitelistList();

    int getFactoryTest();

    ProcessToGcProto getGcProcs(int i);

    int getGcProcsCount();

    List<ProcessToGcProto> getGcProcsList();

    ConfigurationProto getGlobalConfiguration();

    PowerManagerProto.WakeLock getGoingToSleep();

    ProcessRecordProto getHeavyWeightProc();

    ProcessRecordProto getHomeProc();

    ImportanceTokenProto getImportantProcs(int i);

    int getImportantProcsCount();

    List<ImportanceTokenProto> getImportantProcsList();

    ProcessRecordProto getIsolatedProcs(int i);

    int getIsolatedProcsCount();

    List<ProcessRecordProto> getIsolatedProcsList();

    Duration getLastIdleTime();

    int getLastMemoryLevel();

    int getLastNumProcesses();

    long getLastPowerCheckUptimeMs();

    PowerManagerProto.WakeLock getLaunchingActivity();

    long getLowRamSinceLastIdleMs();

    ActivityManagerServiceDumpProcessesProto.LruProcesses getLruProcs();

    int getLruSeq();

    ActivityManagerServiceDumpProcessesProto.MemWatchProcess getMemWatchProcesses();

    String getNativeDebuggingApp();

    ByteString getNativeDebuggingAppBytes();

    int getNewNumServiceProcs();

    int getNumCachedHiddenProcs();

    int getNumNonCachedProcs();

    int getNumServiceProcs();

    ProcessRecordProto getOnHoldProcs(int i);

    int getOnHoldProcsCount();

    List<ProcessRecordProto> getOnHoldProcsList();

    ActivityManagerServiceDumpProcessesProto.PendingTempWhitelist getPendingTempWhitelist(int i);

    int getPendingTempWhitelistCount();

    List<ActivityManagerServiceDumpProcessesProto.PendingTempWhitelist> getPendingTempWhitelistList();

    ProcessRecordProto getPersistentStartingProcs(int i);

    int getPersistentStartingProcsCount();

    List<ProcessRecordProto> getPersistentStartingProcsList();

    ProcessRecordProto getPidsSelfLocked(int i);

    int getPidsSelfLockedCount();

    List<ProcessRecordProto> getPidsSelfLockedList();

    ProcessRecordProto getPreviousProc();

    long getPreviousProcVisibleTimeMs();

    boolean getProcessesReady();

    ProcessRecordProto getProcs(int i);

    int getProcsCount();

    List<ProcessRecordProto> getProcsList();

    ActivityManagerServiceDumpProcessesProto.Profile getProfile();

    ProcessRecordProto getRemovedProcs(int i);

    int getRemovedProcsCount();

    List<ProcessRecordProto> getRemovedProcsList();

    ActivityManagerServiceDumpProcessesProto.Voice getRunningVoice();

    ActivityManagerServiceDumpProcessesProto.ScreenCompatPackage getScreenCompatPackages(int i);

    int getScreenCompatPackagesCount();

    List<ActivityManagerServiceDumpProcessesProto.ScreenCompatPackage> getScreenCompatPackagesList();

    ActivityManagerServiceDumpProcessesProto.SleepStatus getSleepStatus();

    boolean getSystemReady();

    int getTotalPersistentProcs();

    String getTrackAllocationApp();

    ByteString getTrackAllocationAppBytes();

    ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto getUidObservers(int i);

    int getUidObserversCount();

    List<ActivityManagerServiceDumpProcessesProto.UidObserverRegistrationProto> getUidObserversList();

    UserControllerProto getUserController();

    UidRecordProto getValidateUids(int i);

    int getValidateUidsCount();

    List<UidRecordProto> getValidateUidsList();

    VrControllerProto getVrController();

    boolean hasAdjSeq();

    boolean hasAllowLowerMemLevel();

    boolean hasAlwaysFinishActivities();

    boolean hasAppErrors();

    boolean hasBootAnimationComplete();

    boolean hasBooted();

    boolean hasBooting();

    boolean hasCallFinishBooting();

    boolean hasConfigWillChange();

    boolean hasController();

    boolean hasCurrentTracker();

    boolean hasDebug();

    boolean hasFactoryTest();

    boolean hasGlobalConfiguration();

    boolean hasGoingToSleep();

    boolean hasHeavyWeightProc();

    boolean hasHomeProc();

    boolean hasLastIdleTime();

    boolean hasLastMemoryLevel();

    boolean hasLastNumProcesses();

    boolean hasLastPowerCheckUptimeMs();

    boolean hasLaunchingActivity();

    boolean hasLowRamSinceLastIdleMs();

    boolean hasLruProcs();

    boolean hasLruSeq();

    boolean hasMemWatchProcesses();

    boolean hasNativeDebuggingApp();

    boolean hasNewNumServiceProcs();

    boolean hasNumCachedHiddenProcs();

    boolean hasNumNonCachedProcs();

    boolean hasNumServiceProcs();

    boolean hasPreviousProc();

    boolean hasPreviousProcVisibleTimeMs();

    boolean hasProcessesReady();

    boolean hasProfile();

    boolean hasRunningVoice();

    boolean hasSleepStatus();

    boolean hasSystemReady();

    boolean hasTotalPersistentProcs();

    boolean hasTrackAllocationApp();

    boolean hasUserController();

    boolean hasVrController();
}
