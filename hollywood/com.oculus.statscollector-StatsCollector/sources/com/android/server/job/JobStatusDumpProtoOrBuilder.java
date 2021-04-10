package com.android.server.job;

import android.net.NetworkProto;
import com.android.server.job.JobStatusDumpProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface JobStatusDumpProtoOrBuilder extends MessageLiteOrBuilder {
    int getCallingUid();

    String getChangedAuthorities(int i);

    ByteString getChangedAuthoritiesBytes(int i);

    int getChangedAuthoritiesCount();

    List<String> getChangedAuthoritiesList();

    String getChangedUris(int i);

    ByteString getChangedUrisBytes(int i);

    int getChangedUrisCount();

    List<String> getChangedUrisList();

    long getEnqueueDurationMs();

    JobStatusDumpProto.JobWorkItem getExecutingWork(int i);

    int getExecutingWorkCount();

    List<JobStatusDumpProto.JobWorkItem> getExecutingWorkList();

    JobStatusDumpProto.ImplicitConstraints getImplicitConstraints();

    long getInternalFlags();

    boolean getIsDozeWhitelisted();

    boolean getIsExemptedFromAppStandby();

    boolean getIsUidActive();

    JobStatusDumpProto.JobInfo getJobInfo();

    long getLastFailedRunTime();

    long getLastSuccessfulRunTime();

    NetworkProto getNetwork();

    int getNumFailures();

    JobStatusDumpProto.JobWorkItem getPendingWork(int i);

    int getPendingWorkCount();

    List<JobStatusDumpProto.JobWorkItem> getPendingWorkList();

    ConstraintEnum getRequiredConstraints(int i);

    int getRequiredConstraintsCount();

    List<ConstraintEnum> getRequiredConstraintsList();

    ConstraintEnum getSatisfiedConstraints(int i);

    int getSatisfiedConstraintsCount();

    List<ConstraintEnum> getSatisfiedConstraintsList();

    String getSourcePackageName();

    ByteString getSourcePackageNameBytes();

    int getSourceUid();

    int getSourceUserId();

    JobStatusDumpProto.Bucket getStandbyBucket();

    String getTag();

    ByteString getTagBytes();

    long getTimeUntilEarliestRuntimeMs();

    long getTimeUntilLatestRuntimeMs();

    JobStatusDumpProto.TrackingController getTrackingControllers(int i);

    int getTrackingControllersCount();

    List<JobStatusDumpProto.TrackingController> getTrackingControllersList();

    ConstraintEnum getUnsatisfiedConstraints(int i);

    int getUnsatisfiedConstraintsCount();

    List<ConstraintEnum> getUnsatisfiedConstraintsList();

    boolean hasCallingUid();

    boolean hasEnqueueDurationMs();

    boolean hasImplicitConstraints();

    boolean hasInternalFlags();

    boolean hasIsDozeWhitelisted();

    boolean hasIsExemptedFromAppStandby();

    boolean hasIsUidActive();

    boolean hasJobInfo();

    boolean hasLastFailedRunTime();

    boolean hasLastSuccessfulRunTime();

    boolean hasNetwork();

    boolean hasNumFailures();

    boolean hasSourcePackageName();

    boolean hasSourceUid();

    boolean hasSourceUserId();

    boolean hasStandbyBucket();

    boolean hasTag();

    boolean hasTimeUntilEarliestRuntimeMs();

    boolean hasTimeUntilLatestRuntimeMs();
}
